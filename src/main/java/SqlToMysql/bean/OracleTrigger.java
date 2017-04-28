package SqlToMysql.bean;

import SqlToMysql.statement.SqlStmt;

import java.util.List;

/**
 * Oracle触发器实例
 */
public class OracleTrigger {

	private String name;//触发器名称
	private List<String> event;//触发器事件，insert/update等
	private String time;//触发器时间点，before/after等
	private String table;//触发器表
	private String sql;//完整sql语句
	private SqlBlock block;//所在Sql块
	private List<OracleParam> declares;
	private List<SqlStmt> sqlList;//包括Statement与String

	public OracleTrigger(SqlBlock block, String name, List<String> event, String time, String table, String sql,List<OracleParam> declares, List<SqlStmt> sqlList) {
		this.block = block;
		this.name = name;
		this.event = event;
		this.time = time;
		this.table = table;
		this.sql = sql;
		this.declares = declares;
		this.sqlList = sqlList;
	}
	public OracleTrigger(SqlBlock block, String name, String sql) {
		this.block = block;
		this.name = name;
		this.sql = sql;
	}

	public String getName() {
		return name;
	}

	public List<String> getEvent() {
		return event;
	}

	public String getTime() {
		return time;
	}

	public String getTable() {
		return table;
	}

	public String getSql() {
		return sql;
	}

	public SqlBlock getBlock() {
		return block;
	}

	public List<SqlStmt> getSqlList() {
		return sqlList;
	}

	public List<OracleParam> getDeclares() {
		return declares;
	}

	public String toString() {
		return "OracleTrigger:" + this.name;
	}
}
