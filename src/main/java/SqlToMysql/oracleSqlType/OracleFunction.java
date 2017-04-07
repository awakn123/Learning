package SqlToMysql.oracleSqlType;

import SqlToMysql.SqlBlock;
import SqlToMysql.SqlType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OracleFunction extends SqlType {

	public OracleFunction() {
		super(Pattern.compile("^CREATE OR REPLACE FUNCTION \"\\w+\"", Pattern.CASE_INSENSITIVE),
				Pattern.compile(" +end(;)? +/$", Pattern.CASE_INSENSITIVE),
				Pattern.compile("\"\\w+\""),
				"创建存储过程", "procedure");
	}
	public String getContent(SqlBlock block) {
		Matcher m = this.getHeadPattern().matcher(block.sql);
		String sql = this.removeParam(m.replaceAll(""));
		Matcher endM = this.getEndPattern().matcher(sql);
		Matcher commentMatcher = this.getCommentPattern().matcher(endM.replaceAll(""));
		return commentMatcher.replaceAll("").trim();
	}

}
