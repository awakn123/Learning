package SqlToMysql.type.oracleSqlType;

import SqlToMysql.bean.OracleFunction;
import SqlToMysql.bean.OracleParam;
import SqlToMysql.bean.OracleReturn;
import SqlToMysql.bean.SqlBlock;
import SqlToMysql.inter.TypeService;
import SqlToMysql.statement.SqlParserService;
import SqlToMysql.statement.SqlStmt;
import SqlToMysql.type.SqlType;
import SqlToMysql.util.DataTypeConvert;
import SqlToMysql.util.ListUtils;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;
import com.google.common.collect.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OracleFunctionType extends SqlType implements TypeService<OracleFunction> {

	private static final Logger log = LogManager.getLogger(OracleFunctionType.class);
	private static final OracleFunctionType instance = new OracleFunctionType();

	private OracleFunctionType() {
		super(Pattern.compile("^CREATE OR REPLACE FUNCTION \"\\w+\"", Pattern.CASE_INSENSITIVE),
				Pattern.compile(" +end(;)? +/$", Pattern.CASE_INSENSITIVE),
				Pattern.compile("\"\\w+\""),
				"创建函数", "function");
	}

	public static OracleFunctionType getInstance() {
		return instance;
	}

	public String getContent(SqlBlock block) {
		Matcher m = this.getHeadPattern().matcher(block.sql);
		String sql = this.removeParam(m.replaceAll(""));
		Matcher endM = this.getEndPattern().matcher(sql);
		Matcher commentMatcher = this.getCommentPattern().matcher(endM.replaceAll(""));
		return commentMatcher.replaceAll("").trim();
	}

	/**
	 * 将block转为OracleFunction
	 * 处理了名称、参数、返回值、内部命名
	 *
	 * @param block
	 * @return
	 */
	@Override
	public OracleFunction createBean(SqlBlock block, SqlParserService parserService) {
		String sql = block.sql;
		String name = this.getBlockName(block).replaceAll("\"", "");
		sql = this.getHeadPattern().matcher(sql).replaceAll("").trim();
		try {
			// 处理参数
			Pattern paramPattern = Pattern.compile("^\\(\\w+( (in out|in|out))? \\w+(,( )?\\w+( (in|out|in out))? \\w+)*\\)", Pattern.CASE_INSENSITIVE);
			Matcher m = paramPattern.matcher(sql);
			List<OracleParam> params = null;
			if (m.find()) {
				String paramStr = m.group();
				params = OracleParam.createOracleParam(paramStr, OracleParam.TRANSFER_PARAM);
				sql = m.replaceAll("").trim();
			}

			// 处理返回值
			Pattern returnPattern = Pattern.compile("^RETURN \\w+( pipelined)?( authid current_user)? (IS|AS|AGGREGATE USING)", Pattern.CASE_INSENSITIVE);
			m = returnPattern.matcher(sql);
			if (!m.find())
				throw new RuntimeException("无法转换此SQL，找不到返回值,blockName:" + name);
			String returnStr = m.group();
			String[] returnArr = returnStr.split(" ");
			OracleReturn returnType;
			if (returnStr.toUpperCase().indexOf("PIPELINED") > 0)
				returnType = new OracleReturn("PIPELINED");
			else if (returnStr.toUpperCase().indexOf("AGGREGATE USING") > 0)
				returnType = new OracleReturn("AGGREGATE");
			else
				returnType = new OracleReturn(returnArr[1]);
			sql = m.replaceAll("").trim();

			// 处理内部命名
			List<OracleParam> declares = null;
			int beginIndex = sql.toUpperCase().indexOf("BEGIN");
			if (beginIndex > 0) {
				String declareStr = sql.substring(0, beginIndex).trim();
				declares = OracleParam.createOracleParam(declareStr, OracleParam.DECLARE_PARAM);
			}

			// 拿到剩下的部分，做为content,进行下一步处理
			String content = beginIndex > 0 ? sql.substring(beginIndex, sql.length()) : sql;
			boolean hasBegin = Pattern.compile("^BEGIN", Pattern.CASE_INSENSITIVE).matcher(content).find();
			boolean hasEnd = Pattern.compile("END( " + name + ")?( )?;( )?(/)?$", Pattern.CASE_INSENSITIVE).matcher(content).find();
			if (hasBegin && hasEnd) {
				int beginIdx = content.toUpperCase().indexOf("BEGIN");
				int endIdx = content.toUpperCase().lastIndexOf("END");
				content = content.substring(beginIdx + 5, endIdx);
//				List<SQLStatement> list = SQLUtils.parseStatements(content, JdbcConstants.ORACLE);
//				List<SqlStmt> list = DruidSqlParser.parse(content, name);
				List<SqlStmt> list = parserService.parse(content, name);
				return new OracleFunction(name, params, returnType, declares, list, block, hasBegin, hasEnd);
			} else
				return new OracleFunction(name, params, returnType, declares, content, block, hasBegin, hasEnd);
		} catch (Exception e) {
			log.error("读取block出错，blockName:" + name, e);
			return null;
		}
	}

	/**
	 * 将oracleFunction转为Mysql语法。不涉及具体逻辑，只转换外层。
	 *
	 * @param func
	 * @return
	 */
	public String toMysqlSyntax(OracleFunction func, Function<Appendable, SQLASTVisitor> f) {
		StringBuffer sb = new StringBuffer();
		sb.append("-- -------------------------------------------------------------------------------------------\n");
		sb.append("-- ").append(func.getName()).append("\n");
		if ("PIPELINED".equals(func.getReturnType().getType()) || "AGGREGATE".equals(func.getReturnType().getType())) {
			sb.append("-- ReturnType: ").append(func.getReturnType().getType()).append("\n");
			log.info("returnType:" + func.getReturnType().getType() + ", name:" + func.getName());
		}
		sb.append("-- -------------------------------------------------------------------------------------------\n");
		sb.append("DROP FUNCTION IF EXISTS ").append(func.getName()).append(";\n");//初始化
		sb.append("DELIMITER$$\n");//初始化
		sb.append("CREATE FUNCTION ").append(func.getName());//函数名修改
		if (func.getParams() == null || func.getParams().isEmpty()) {
			sb.append("()\n");
		} else {
			List<String> paramStrs = Lists.newArrayList();
			func.getParams().stream().forEach((param) -> {

				paramStrs.add(param.toString());
			});
			sb.append("(").append(ListUtils.toString(paramStrs)).append(")\n");
		}
		String returnType = DataTypeConvert.oracleToMysql(func.getReturnType().getType());
		sb.append("RETURNS ").append(returnType);
		if (returnType != null && "varchar".equals(returnType.toLowerCase()))
			sb.append("(255)");
		sb.append("\n");

		if (func.hasBegin()) {
			sb.append("BEGIN\n");
			if (func.getDeclareList() != null) {
				for (OracleParam op : func.getDeclareList()) {
					sb.append(op.toDeclareString()).append("\n");
				}
			}
			if (func.hasEnd()) {
				func.getSqlList().stream().forEach(sql -> sql.append(sb, f));
				sb.append("END$$\n");
			} else {
				int beginIdx = func.getContent().toUpperCase().indexOf("BEGIN");
				sb.append(func.getContent().substring(beginIdx + 5));
			}
		} else
			sb.append(func.getContent());
		if (!func.hasBegin() || !func.hasEnd()) {
//			throw new RuntimeException(func.getContent());
			log.info("NOT BEGIN END, name:" + func.getName() + ", content:" + func.getContent());
		}
		sb.append("DELIMITER;\n");
		return sb.toString();
	}
}
