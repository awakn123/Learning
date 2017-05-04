package SqlToMysql.bean;

import SqlToMysql.statement.SqlStmt;

import java.util.List;

public class OracleFunction extends OracleBean{
	private OracleReturn returnType;//返回类型
	private String content;
	private boolean hasBegin;
	private boolean hasEnd;


	public OracleReturn getReturnType() {
		return returnType;
	}

	public String getContent() {
		return content;
	}

	public boolean hasBegin() {
		return hasBegin;
	}

	public boolean hasEnd() {
		return hasEnd;
	}


	public OracleFunction(String name, List<OracleParam> params, OracleReturn returnType, List<OracleParam> declare, String content, SqlBlock block, boolean hasBegin, boolean hasEnd) {
		super(name, "", block, declare, null, params);
		this.returnType = returnType;
		this.content = content;
		this.hasBegin = hasBegin;
		this.hasEnd = hasEnd;
	}

	public OracleFunction(String name, List<OracleParam> params, OracleReturn returnType, List<OracleParam> declare, List<SqlStmt> sqlList, SqlBlock block, boolean hasBegin, boolean hasEnd) {
		super(name, "", block, declare,sqlList, params);
		this.returnType = returnType;
		this.hasBegin = hasBegin;
		this.hasEnd = hasEnd;
	}

	public String toString(){
		return "OracleFunction:" + this.getName();
	}
}
