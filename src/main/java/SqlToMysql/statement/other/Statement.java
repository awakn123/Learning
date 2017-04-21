package SqlToMysql.statement.other;

public class Statement {
	private AbstractStatementType type;
	private String sql;

	public Statement(AbstractStatementType type, String sql) {
		this.type = type;
		this.sql = sql;
	}

	public AbstractStatementType getType() {
		return type;
	}

	public String getSql() {
		return sql;
	}
}
