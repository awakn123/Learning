package SqlToMysql;

import SqlToMysql.statement.DruidSqlParser;
import SqlToMysql.statement.O2MVisitor;
import com.alibaba.druid.sql.ast.SQLStatement;

public class SqlParser {
	public static void main(String[] args) {
	    String sql = "SELECT COUNT(1)\n" +
				"FROM USER_TAB_COLUMNS\n" +
				"WHERE TABLE_NAME = 'INITSERVICEXMLSTATE'\n" +
				"\tAND COLUMN_NAME = 'E8CLEAR'";
		SQLStatement s = DruidSqlParser.parseByDruid(sql);
		StringBuilder out = new StringBuilder();
		s.accept(new O2MVisitor(out, false));
		System.out.println(out.toString());
	}
}
