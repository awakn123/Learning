package SqlToMysql.type.oracleSqlType;

import SqlToMysql.bean.SqlBlock;
import SqlToMysql.type.SqlType;
import SqlToMysql.bean.OracleFunction;
import SqlToMysql.inter.BeanCreate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OracleFunctionType extends SqlType implements BeanCreate<OracleFunction> {

	private static final OracleFunctionType instance = new OracleFunctionType();
	private OracleFunctionType() {
		super(Pattern.compile("^CREATE OR REPLACE FUNCTION \"\\w+\"", Pattern.CASE_INSENSITIVE),
				Pattern.compile(" +end(;)? +/$", Pattern.CASE_INSENSITIVE),
				Pattern.compile("\"\\w+\""),
				"创建存储过程", "procedure");
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

	@Override
	public OracleFunction createBean(SqlBlock block) {
		return null;
	}
}
