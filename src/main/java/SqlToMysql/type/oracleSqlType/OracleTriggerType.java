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
		Matcher m = this.getHeadPattern().matcher(sql);
		sql = this.removeParam(m.replaceAll("")).trim();
		String[] sqlSplits = sql.split(" ");
		String time = sqlSplits.length > 0 ? sqlSplits[0] : null;
		String event = sqlSplits.length > 1 ? sqlSplits[1] : null;
		String table = sqlSplits.length > 3 ? sqlSplits[3] : null;
		return new OracleTrigger(block, name, event, time, table, block.sql);
	}

}
