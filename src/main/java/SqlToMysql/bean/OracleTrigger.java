package SqlToMysql.bean;

/**
 * Oracle触发器实例
 */
public class OracleTrigger {

	private String name;//触发器名称
	private String event;//触发器事件，insert/update等
	private String time;//触发器时间点，before/after等
	private String table;//触发器表
	private String sql;//完整sql语句
	private SqlBlock block;//所在Sql块

	public OracleTrigger(SqlBlock block, String name, String event, String time, String table, String sql) {
		this.block = block;
		this.name = name;
		this.event = event;
		this.time = time;
		this.table = table;
		this.sql = sql;
	}

	public String getName() {
		return name;
	}

	public String getEvent() {
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
}
