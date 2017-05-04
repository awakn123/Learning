package SqlToMysql.bean;

import SqlToMysql.statement.SqlStmt;

import java.util.List;

/**
 * Oracle触发器实例
 */
public class OracleTrigger extends OracleBean {

	private List<String> event;//触发器事件，insert/update等
	private String time;//触发器时间点，before/after等
	private String table;//触发器表

	public OracleTrigger(SqlBlock block, String name, List<String> event, String time, String table, String sql,List<OracleParam> declares, List<SqlStmt> sqlList) {
		super(name, sql, block, declares,sqlList, null);
		this.event = event;
		this.time = time;
		this.table = table;
	}
	public OracleTrigger(String name, String sql, SqlBlock block){
		super(name,sql,block);
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

	public String toString() {
		return "OracleTrigger:" + this.getName();
	}
}
