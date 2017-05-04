package SqlToMysql.bean;

import SqlToMysql.statement.SqlStmt;

import java.util.List;

public class OracleBean {
	private String name;//触发器名称
	private String sql;//完整sql语句
	private SqlBlock block;//所在Sql块
	private List<OracleParam> declares;
	private List<SqlStmt> sqlList;//包括Statement与String
	private List<OracleParam> params;//传参

	public OracleBean(){}
	public OracleBean(SqlBlock block){
		this.block = block;
	}
	public OracleBean(String name, String sql, SqlBlock block, List<OracleParam> declares, List<SqlStmt> sqlList,List<OracleParam> params) {
		this.name = name;
		this.sql = sql;
		this.block = block;
		this.declares = declares;
		this.sqlList = sqlList;
		this.params = params;
	}

	public OracleBean(String name, String sql, SqlBlock block) {
		this.name = name;
		this.sql = sql;
		this.block = block;
	}

	public String getName() {
		return name;
	}

	public String getSql() {
		return sql;
	}

	public SqlBlock getBlock() {
		return block;
	}

	public List<OracleParam> getDeclares() {
		return declares;
	}

	public List<SqlStmt> getSqlList() {
		return sqlList;
	}

	public List<OracleParam> getParams() {
		return params;
	}
}
