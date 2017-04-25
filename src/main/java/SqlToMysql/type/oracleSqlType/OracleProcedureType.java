package SqlToMysql.type.oracleSqlType;

import SqlToMysql.bean.OracleProcedure;
import SqlToMysql.bean.SqlBlock;
import SqlToMysql.inter.TypeService;
import SqlToMysql.statement.SqlParserService;
import SqlToMysql.type.SqlType;
import SqlToMysql.util.SqlUtils;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OracleProcedureType extends SqlType implements TypeService<OracleProcedure> {

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
	public OracleProcedure createBean(SqlBlock block, SqlParserService parser) {
		return null;
	}

	@Override
	public String toMysqlSyntax(OracleProcedure oracleProcedure, Function<Appendable, SQLASTVisitor> f) {
		return null;
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
