package SqlToMysql;

import com.google.common.collect.Lists;

import java.util.List;

public class SqlBlock {
	public String sql;
	public String content;
	String fileName;
	int start;
	int end;
	private SqlType sqlType;
	List<String> sqlList;
	String name;
	SqlType.SingleType type;

	public SqlType getSqlType() {
		return sqlType;
	}

	public void setSqlType(SqlType sqlType) {
		this.sqlType = sqlType;
	}

	public SqlBlock(String sql, String name) {
		this.sql = sql;
		this.fileName = name;
	}

	public SqlBlock(int start, String fileName) {
		this.start = start;
		this.fileName = fileName;
	}

	public void splitToObject() {
		if (sqlType == null) {
			return;
		}
		this.content = this.sqlType.getContent(this);
		sqlList = Lists.newArrayList(this.content.split(";"));
		this.name = this.sqlType.getBlockName(this);
		this.type = this.sqlType.getBlockType(this);
	}
}
