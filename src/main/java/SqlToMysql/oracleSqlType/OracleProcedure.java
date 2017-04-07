package SqlToMysql.oracleSqlType;

import SqlToMysql.SqlBlock;
import SqlToMysql.SqlType;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OracleProcedure extends SqlType {

	public OracleProcedure() {
		super(Pattern.compile("^CREATE OR REPLACE PROCEDURE \"\\w+\"(( *)\\(.*\\))?( *)AS( +)(BEGIN( +))?", Pattern.CASE_INSENSITIVE),
				Pattern.compile(" +end; +/$", Pattern.CASE_INSENSITIVE),
				Pattern.compile("\"\\w+\""),
				"创建存储过程", "procedure");
	}
	public String getContent(SqlBlock block) {
		Matcher m = this.getPattern().matcher(block.sql);

		Matcher endM = this.getEndPattern().matcher(m.replaceAll(""));
		Matcher commentMatcher = this.getCommentPattern().matcher(endM.replaceAll(""));
		return commentMatcher.replaceAll("").trim();
	}

	public class ProcedureType {
		String type;
		private ProcedureType(String type){
			this.type = type;
		}
		public List<ProcedureType> getTypes(){
			return Lists.newArrayList(
					new ProcedureType("select")
			);
		}
	}
}
