package SqlToMysql.type.oracleSqlType;

import SqlToMysql.bean.OracleParam;
import SqlToMysql.bean.OracleProcedure;
import SqlToMysql.bean.SqlBlock;
import SqlToMysql.statement.SqlParserService;
import SqlToMysql.statement.SqlStmt;
import SqlToMysql.type.SqlType;
import SqlToMysql.type.TypeService;
import SqlToMysql.util.SqlUtils;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;
import com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OracleProcedureType extends SqlType implements TypeService<OracleProcedure> {
	private static final Logger log = LogManager.getLogger();
	private static volatile OracleProcedureType instance;
	private static Pattern prefix = Pattern.compile("( *)(AS|IS)( +)(BEGIN( +))?", Pattern.CASE_INSENSITIVE);
	private OracleProcedureType() {
		super(Pattern.compile("^CREATE OR REPLACE PROCEDURE \"\\w+\"", Pattern.CASE_INSENSITIVE),
				Pattern.compile(" +end(;)? +/$", Pattern.CASE_INSENSITIVE),
				Pattern.compile("\"\\w+\""),
				"创建存储过程", "procedure");
	}


	public static OracleProcedureType getInstance(){
		if (instance != null) {
			return instance;
		}
		synchronized(OracleProcedureType.class) {
			if (instance == null)
				instance =  new OracleProcedureType();
		}
		return instance;
	}
	public String getContent(SqlBlock block) {
		Matcher m = this.getHeadPattern().matcher(block.sql);
		String sql = this.removeParam(m.replaceAll(""));
		Matcher prefixM = prefix.matcher(sql);
		Matcher endM = this.getEndPattern().matcher(prefixM.replaceAll(""));
		Matcher commentMatcher = this.getCommentPattern().matcher(endM.replaceAll(""));
		return commentMatcher.replaceAll("").trim();
	}



	public static SingleType getBlockType(SqlBlock sqlBlock) {
		if (sqlBlock == null || sqlBlock.getSqlList() == null || sqlBlock.getSqlList().isEmpty()) {
			return SingleType.empty;
		}
		if (sqlBlock.getSqlList().size() >= 2) {
			return SingleType.many;
		}
		String sql = sqlBlock.getSqlList().get(0);
		return Arrays.stream(SingleType.values()).filter(singleType -> singleType.check(sql)).findFirst().orElse(SingleType.other);
	}

	@Override
	public OracleProcedure createBean(SqlBlock block, SqlParserService parserService) {
		String sql = block.sql;
		String name = this.getBlockName(block).replaceAll("\"", "");
		sql = this.getHeadPattern().matcher(sql).replaceAll("").trim();
		try {
			// 处理参数
			Pattern paramPattern = Pattern.compile("^\\(( )?\\w+( (in out|in|out))? \\w+( )?(,( )?\\w+( (in|out|in out))? (\\w|\\.)+( )?)*\\)", Pattern.CASE_INSENSITIVE);
			Matcher m = paramPattern.matcher(sql);
			List<OracleParam> params = null;
			if (m.find()) {
				String paramStr = m.group();
				params = OracleParam.createOracleParam(paramStr, OracleParam.TRANSFER_PARAM);
				sql = m.replaceAll("").trim();
			}

			// 处理内部命名
			List<OracleParam> declares = null;
			int beginIndex = sql.toUpperCase().indexOf("BEGIN ");
			if (beginIndex > 0) {
				String declareStr = sql.substring(0, beginIndex).trim();
				if (!declareStr.equalsIgnoreCase("as") && !declareStr.equalsIgnoreCase("is")) {
					if (declareStr.toUpperCase().startsWith("AS ") || declareStr.toUpperCase().startsWith("IS "))
						declareStr = declareStr.substring(3);
					declares = OracleParam.createOracleParam(declareStr, OracleParam.DECLARE_PARAM);
				}
			}

			// 拿到剩下的部分，做为content,进行下一步处理
			String content = beginIndex > 0 ? sql.substring(beginIndex, sql.length()) : sql;
			boolean hasBegin = Pattern.compile("^BEGIN", Pattern.CASE_INSENSITIVE).matcher(content).find();
			boolean hasEnd = Pattern.compile("END( " + name + ")?( )?;( )?(/)?$", Pattern.CASE_INSENSITIVE).matcher(content).find();
			if (hasBegin && hasEnd) {
				int beginIdx = content.toUpperCase().indexOf("BEGIN");
				int endIdx = content.toUpperCase().lastIndexOf("END");
				content = content.substring(beginIdx + 5, endIdx);
				List<SqlStmt> list = parserService.parse(content, name);
				return new OracleProcedure(name, params, declares, list, block, hasBegin, hasEnd);
			} else
				return new OracleProcedure(name, params, declares, null, block, hasBegin, hasEnd);
		} catch (Exception e) {
			log.error("读取block出错，blockName:" + name, e);
			return null;
		}
	}

	@Override
	public String toMysqlSyntax(OracleProcedure proc, Function<Appendable, SQLASTVisitor> f) {
		StringBuffer sb = new StringBuffer();
		sb.append("-- -------------------------------------------------------------------------------------------\n");
		sb.append("-- ").append(proc.getName()).append("\n");
		sb.append("-- -------------------------------------------------------------------------------------------\n");
		sb.append("DROP PROCEDURE IF EXISTS ").append(proc.getName()).append(";\n");//初始化
		sb.append("DELIMITER$$\n");//初始化
		sb.append("CREATE PROCEDURE ").append(proc.getName());//函数名修改
		sb.append(OracleParam.listToString(proc.getParams()));
		sb.append("\n");
		sb.append("BEGIN\n");
		if (proc.getDeclares() != null) {
			for (OracleParam op : proc.getDeclares()) {
				sb.append(op.toDeclareString()).append("\n");
			}
		}
		proc.getSqlList().stream().forEach(sql -> sql.append(sb, f));
		sb.append("END$$\n");
		sb.append("DELIMITER;\n");
		return sb.toString();
	}

	/**
	 * 统计各个block的内容，判断存储过程数
	 * @param blocks
	 */
	public static void classifiedByContent(List<SqlBlock> blocks) {
		Map<SqlType, List<SqlBlock>> typeToBlockMap = SqlUtils.classfiedBySqlType(blocks);
		Map<OracleProcedureType.SingleType, Integer> blockNumMap = Maps.newHashMap();
		Map<OracleProcedureType.SingleType, List<SqlBlock>> blockMap = Maps.newHashMap();
		typeToBlockMap.get(OracleProcedureType.getInstance()).forEach(sqlBlock -> {
			OracleProcedureType.SingleType type = OracleProcedureType.getBlockType(sqlBlock);
			int i = blockNumMap.getOrDefault(type, 0);
			blockNumMap.put(type, i + 1);
			blockMap.putIfAbsent(type, new ArrayList<SqlBlock>());
			blockMap.get(type).add(sqlBlock);
		});
		blockNumMap.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + ":" + entry.getValue()));
		System.out.println(blocks.size());
	}

	public enum SingleType {
		selectinto("^SELECT INTO"), cursor("OPEN|FOR"),
		select("^SELECT"), update("^UPDATE"), insert("^INSERT"), delete("^DELETE"),
		many("BEGIN"), empty, other;
		private Pattern pattern;

		private SingleType() {
		}

		private SingleType(String p) {
			this.pattern = Pattern.compile(p, Pattern.CASE_INSENSITIVE);
		}

		public boolean check(String sql) {
			if (pattern == null)
				return false;
			Matcher m = this.pattern.matcher(sql);
			return m.find();
		}
	}

}
