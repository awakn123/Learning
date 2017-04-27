package SqlToMysql.type.oracleSqlType;

import SqlToMysql.bean.OracleParam;
import SqlToMysql.bean.OracleTrigger;
import SqlToMysql.bean.SqlBlock;
import SqlToMysql.inter.TypeService;
import SqlToMysql.statement.SqlParserService;
import SqlToMysql.statement.SqlStmt;
import SqlToMysql.type.SqlType;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OracleTriggerType extends SqlType  implements TypeService<OracleTrigger> {

	private static class OracleTriggerTypeHolder {
		private static final OracleTriggerType instance = new OracleTriggerType();
	}
	private static Pattern prefix = Pattern.compile("( *)(AS|IS)( +)(BEGIN( +))?", Pattern.CASE_INSENSITIVE);
	private OracleTriggerType() {
		super(Pattern.compile("^CREATE OR REPLACE TRIGGER \"\\w+\"", Pattern.CASE_INSENSITIVE),
				Pattern.compile(" +end(;)? +/$", Pattern.CASE_INSENSITIVE),
				Pattern.compile("\"\\w+\""),
				"创建触发器", "trigger");
	}

	public static final OracleTriggerType getInstance() {
		return OracleTriggerTypeHolder.instance;
	}

	public String getContent(SqlBlock block) {
		Matcher m = this.getHeadPattern().matcher(block.sql);
		String sql = this.removeParam(m.replaceAll(""));
		Matcher prefixM = prefix.matcher(sql);
		Matcher endM = this.getEndPattern().matcher(prefixM.replaceAll(""));
		Matcher commentMatcher = this.getCommentPattern().matcher(endM.replaceAll(""));
		return commentMatcher.replaceAll("").trim();
	}

	/**
	 * 从block转为OracleTrigger实体类
	 * @param block
	 * @return
	 */
	@Override
	public OracleTrigger createBean(SqlBlock block, SqlParserService parser) {

		String sql = block.sql;
		String name = this.getBlockName(block);
		name = name.replaceAll("\"", "");
		Matcher m = this.getHeadPattern().matcher(sql);
		sql = m.replaceAll("").trim();
		Pattern p = Pattern.compile("^(BEFORE|AFTER) \\w+ ON \"\\w+\" REFERENCING OLD AS \"OLD\" NEW AS \"NEW\" FOR EACH ROW ENABLE", Pattern.CASE_INSENSITIVE);
		m = p.matcher(sql);
		String time, event, table;
		if (m.find()) {
			String[] sqlSplits = m.group().split(" ");
			time = sqlSplits.length > 0 ? sqlSplits[0] : null;
			event = sqlSplits.length > 1 ? sqlSplits[1] : null;
			table = sqlSplits.length > 3 ? sqlSplits[3] : null;
			table = table.replaceAll("\"", "");
		} else {
			Pattern p2 = Pattern.compile("^(BEFORE|AFTER) \\w+( OR \\w+)*( OF \"\\w+\")? ON \"\\w+\" REFERENCING OLD AS \"OLD\" NEW AS \"NEW\" FOR EACH ROW ENABLE", Pattern.CASE_INSENSITIVE);
			m = p2.matcher(sql);
			if (!m.find()) {
				System.out.println("error:"+sql);
				return new OracleTrigger(block, name, block.sql);
			}
			int firstSpaceIdx = sql.indexOf(" ");
			time = sql.substring(0, firstSpaceIdx);
			sql = sql.substring(firstSpaceIdx + 1);
			int onIndex = sql.toUpperCase().indexOf("ON");
			event = sql.substring(0, onIndex - 1);
			sql = sql.substring(onIndex + 3);
			firstSpaceIdx = sql.indexOf(" ");
			table = sql.substring(0, firstSpaceIdx);
			table = table.replaceAll("\"", "");
		}
		sql = m.replaceAll("");

		// 处理内部命名
		List<OracleParam> declares = null;
		int beginIndex = sql.toUpperCase().indexOf("BEGIN");
		if (beginIndex > 0) {
			String declareStr = sql.substring(0, beginIndex).trim();
			declares = OracleParam.createOracleParam(declareStr, OracleParam.DECLARE_PARAM);
		}

		String content = beginIndex > 0 ? sql.substring(beginIndex, sql.length()) : sql;
		boolean hasBegin = Pattern.compile("^BEGIN", Pattern.CASE_INSENSITIVE).matcher(content).find();
		boolean hasEnd = Pattern.compile("END( " + name + ")?( )?;( )?(/)?$", Pattern.CASE_INSENSITIVE).matcher(content).find();
		if (hasBegin && hasEnd) {
			int beginIdx = content.toUpperCase().indexOf("BEGIN ");
			if (beginIdx == -1)
				beginIdx = content.toUpperCase().indexOf("BEGIN\n");
			int endIdx = content.toUpperCase().lastIndexOf("END");
			content = content.substring(beginIdx + 5, endIdx);
			List<SqlStmt> sqlList = parser.parse(content, name);
			return new OracleTrigger(block, name, event, time, table, block.sql, declares, sqlList);
		} else
			return new OracleTrigger(block, name, event, time, table, block.sql, declares, null);
	}

	@Override
	public String toMysqlSyntax(OracleTrigger tr, Function<Appendable, SQLASTVisitor> f) {
		StringBuffer sb = new StringBuffer();
		sb.append("-- -------------------------------------------------------------------------------------------\n");
		sb.append("-- ").append(tr.getName()).append("\n");
		sb.append("-- -------------------------------------------------------------------------------------------\n");
		//初始化
		sb.append("DROP TRIGGER IF EXISTS ").append(tr.getName()).append(";\n");
		sb.append("DELIMITER$$\n");
		sb.append("CREATE TRIGGER ").append(tr.getName()).append(" ").append(tr.getTime()).append(" ")
				.append(tr.getEvent()).append(" ON ").append(tr.getTable()).append("\n");
		sb.append("BEGIN\n");
		if (tr.getDeclares() != null) {
			for (OracleParam op : tr.getDeclares()) {
				sb.append(op.toDeclareString()).append("\n");
			}
		}
		tr.getSqlList().stream().forEach(sql -> sql.append(sb, f));
		sb.append("END$$\n");
		sb.append("DELIMITER;\n");
		return sb.toString();
	}

	public enum TriggerEnum{
		autoIncrement,other
		;
		public static TriggerEnum getBySql(String sql) {
			Pattern headP = Pattern.compile("^CREATE OR REPLACE TRIGGER \"\\w+\" BEFORE INSERT ON \"\\w+\" REFERENCING OLD AS \"OLD\" NEW AS \"NEW\" FOR EACH ROW ENABLE ", Pattern.CASE_INSENSITIVE);
			Pattern contentP = Pattern.compile("^(WHEN \\(new\\.id is null\\) )?begin select \\w+\\.nextval into( )?:new\\.\\w+ from (sys\\.)?dual;( )?end( )?;$", Pattern.CASE_INSENSITIVE);
			Matcher m = headP.matcher(sql);
			sql = m.replaceAll("");
			m = contentP.matcher(sql.trim());
			return m.find()? autoIncrement : other;
		}
		public static String getAutoIncrementIdCol(String sql) {
			Pattern headP = Pattern.compile("^CREATE OR REPLACE TRIGGER \"\\w+\" BEFORE INSERT ON \"\\w+\" REFERENCING OLD AS \"OLD\" NEW AS \"NEW\" FOR EACH ROW ENABLE ", Pattern.CASE_INSENSITIVE);
			Pattern contentP = Pattern.compile("^(WHEN \\(new\\.id is null\\) )?begin select \\w+\\.nextval into( )?:new\\.", Pattern.CASE_INSENSITIVE);
			Pattern endP = Pattern.compile(" from (sys\\.)?dual;( )?end( )?;$", Pattern.CASE_INSENSITIVE);
			Matcher m = headP.matcher(sql);
			sql = m.replaceAll("");
			m = contentP.matcher(sql.trim());
			sql = m.replaceAll("");
			m = endP.matcher(sql);
			return m.replaceAll("");
		}
	}

}
