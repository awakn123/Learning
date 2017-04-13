package SqlToMysql.type.oracleSqlType;

import SqlToMysql.bean.OracleTrigger;
import SqlToMysql.bean.SqlBlock;
import SqlToMysql.inter.BeanCreate;
import SqlToMysql.type.SqlType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OracleTriggerType extends SqlType  implements BeanCreate<OracleTrigger> {

	private static class OracleTriggerTypeHolder {
		private static final OracleTriggerType instance = new OracleTriggerType();
	}
	private static Pattern prefix = Pattern.compile("( *)(AS|IS)( +)(BEGIN( +))?", Pattern.CASE_INSENSITIVE);
	private OracleTriggerType() {
		super(Pattern.compile("^CREATE OR REPLACE TRIGGER \"\\w+\"", Pattern.CASE_INSENSITIVE),
				Pattern.compile(" +end(;)? +/$", Pattern.CASE_INSENSITIVE),
				Pattern.compile("\"\\w+\""),
				"创建存储过程", "procedure");
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
	public OracleTrigger createBean(SqlBlock block) {
		String sql = block.sql;
		String name = this.getBlockName(block);
		name = name.replaceAll("\"", "");
		Matcher m = this.getHeadPattern().matcher(sql);
		sql = this.removeParam(m.replaceAll("")).trim();
		Pattern p = Pattern.compile("^(BEFORE|AFTER) \\w+ ON \"\\w+\"", Pattern.CASE_INSENSITIVE);
		m = p.matcher(sql);
		if (m.find()) {
			String[] sqlSplits = sql.split(" ");
			String time = sqlSplits.length > 0 ? sqlSplits[0] : null;
			String event = sqlSplits.length > 1 ? sqlSplits[1] : null;
			String table = sqlSplits.length > 3 ? sqlSplits[3] : null;
			table = table.replaceAll("\"", "");
			return new OracleTrigger(block, name, event, time, table, block.sql);
		} else {
			Pattern p2 = Pattern.compile("^(BEFORE|AFTER) \\w+( OR \\w+)*( OF \"\\w+\")? ON \"\\w+\"", Pattern.CASE_INSENSITIVE);
			m = p2.matcher(sql);
			if (!m.find()) {
				System.out.println("error:"+sql);
				return new OracleTrigger(block, name, null, null, null, block.sql);
			}
			int firstSpaceIdx = sql.indexOf(" ");
			String time = sql.substring(0, firstSpaceIdx);
			sql = sql.substring(firstSpaceIdx + 1);
			int onIndex = sql.toUpperCase().indexOf("ON");
			String event = sql.substring(0, onIndex - 1);
			sql = sql.substring(onIndex + 3);
			firstSpaceIdx = sql.indexOf(" ");
			String table = sql.substring(0, firstSpaceIdx);
			table = table.replaceAll("\"", "");
			return new OracleTrigger(block, name, event, time, table, block.sql);
		}
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
