package SqlToMysql.bean;

import SqlToMysql.statement.SqlStmt;

import java.util.List;

public class OracleFunction {
	private String name;//函数名称
	private List<OracleParam> params;//传参
	private OracleReturn returnType;//返回类型
	private List<OracleParam> declareList;//命名定义部分
	private List<SqlStmt> sqlList;//包括Statement与String
	private String content;
	private SqlBlock block;
	private boolean hasBegin;
	private boolean hasEnd;

	public String getName() {
		return name;
	}

	public List<OracleParam> getParams() {
		return params;
	}

	public OracleReturn getReturnType() {
		return returnType;
	}

	public String getContent() {
		return content;
	}

	public SqlBlock getBlock() {
		return block;
	}

	public List<OracleParam> getDeclareList() {
		return declareList;
	}

	public boolean hasBegin() {
		return hasBegin;
	}

	public boolean hasEnd() {
		return hasEnd;
	}

	public List<SqlStmt> getSqlList() {
		return sqlList;
	}

	public OracleFunction(String name, List<OracleParam> params, OracleReturn returnType, List<OracleParam> declare, String content, SqlBlock block, boolean hasBegin, boolean hasEnd) {
		this.name = name;
		this.params = params;
		this.returnType = returnType;
		this.declareList = declare;
		this.content = content;
		this.block = block;
		this.hasBegin = hasBegin;
		this.hasEnd = hasEnd;
	}

	public OracleFunction(String name, List<OracleParam> params, OracleReturn returnType, List<OracleParam> declare, List<SqlStmt> sqlList, SqlBlock block, boolean hasBegin, boolean hasEnd) {
		this.name = name;
		this.params = params;
		this.returnType = returnType;
		this.declareList = declare;
		this.sqlList = sqlList;
		this.block = block;
		this.hasBegin = hasBegin;
		this.hasEnd = hasEnd;
	}

	public String toString(){
		return "OracleFunction:" + this.name;
	}
}
