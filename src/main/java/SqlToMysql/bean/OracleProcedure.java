package SqlToMysql.bean;

import SqlToMysql.statement.SqlStmt;

import java.util.List;

public class OracleProcedure extends OracleBean{
	private boolean hasBegin;
	private boolean hasEnd;

	public OracleProcedure(String name, List<OracleParam> params, List<OracleParam> declares, List<SqlStmt> list, SqlBlock block, boolean hasBegin, boolean hasEnd) {
		super(name, block.sql, block, declares, list, params);
		this.hasBegin = hasBegin;
		this.hasEnd = hasEnd;
	}

	public String toString(){
		return "OracleProcedure:" + this.getName();
	}
}
