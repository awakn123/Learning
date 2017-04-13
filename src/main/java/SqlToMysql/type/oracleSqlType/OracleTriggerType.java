package SqlToMysql.type.oracleSqlType;

import SqlToMysql.bean.SqlBlock;
import SqlToMysql.type.SqlType;
import SqlToMysql.bean.OracleTrigger;
import SqlToMysql.inter.BeanCreate;

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

	@Override
	public OracleTrigger createBean(SqlBlock block) {
		return null;
	}

}
