package SqlToMysql.statement;

public class SqlStmt<T> {
	private T statement;
	private String errorMsg;

	public SqlStmt(T statement) {
		this.statement = statement;
	}
	public SqlStmt(T statement, String errorMsg) {
		this.statement = statement;
		this.errorMsg = errorMsg;
	}

	public T getStatement() {
		return statement;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
