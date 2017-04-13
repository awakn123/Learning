package SqlToMysql.type.oracleSqlType;

import SqlToMysql.bean.SqlBlock;
import SqlToMysql.type.SqlType;
import SqlToMysql.bean.OracleProcedure;
import SqlToMysql.inter.BeanCreate;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OracleProcedureType extends SqlType implements BeanCreate<OracleProcedure> {

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
	public OracleProcedure createBean(SqlBlock block) {
		return null;
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
