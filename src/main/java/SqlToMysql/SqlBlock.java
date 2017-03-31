package SqlToMysql;

public class SqlBlock {
	String sql;
	String fileName;

	public SqlBlock(String sql, String name) {
		this.sql = sql;
		this.fileName = name;
	}
}
