package SqlToMysql.bean;

import SqlToMysql.statement.SqlStmt;

import java.util.List;

public class OracleView extends OracleBean {
	public OracleView(String name, String sql, SqlBlock block, List<SqlStmt> sqlList) {
		super(name, sql, block, null, sqlList, null);
	}
}
