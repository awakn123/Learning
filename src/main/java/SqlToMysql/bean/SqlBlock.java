package SqlToMysql.bean;

import SqlToMysql.util.SqlUtils;
import SqlToMysql.type.SqlType;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SqlBlock {
	private SqlFile sqlFile;
	private SqlType sqlType;
	private int start;
	private int end;
	private List<String> sqlList;


	String name;
	public String sql;
	public String content;

	public SqlType getSqlType() {
		return sqlType;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public List<String> getSqlList() {
		return sqlList;
	}

	public String getName() {
		return name;
	}

	public void setSqlType(SqlType sqlType) {
		this.sqlType = sqlType;
	}

	public SqlBlock(String sql, SqlFile sqlFile) {
		this.sql = sql;
		this.sqlFile = sqlFile;
		sqlList = Lists.newArrayList();
	}

	public SqlBlock(int start, SqlFile sqlFile) {
		this.start = start;
		this.sqlFile = sqlFile;
		sqlList = Lists.newArrayList();
	}

	public SqlBlock(SqlFile sqlFile, int start, int end) {
		this.start = start;
		this.end = end;
		this.sqlFile = sqlFile;
		this.sqlList = Lists.newArrayList(sqlFile.getContentList().subList(start, end + 1));
		this.sql = SqlUtils.mergeAndTrim(SqlUtils.stripComment(this.sqlList));
	}

	public SqlFile getSqlFile() {
		return sqlFile;
	}

	public void splitToObject() {
		if (sqlType == null) {
			return;
		}
		this.content = this.sqlType.getContent(this);
//		sqlList = Lists.newArrayList(this.content.split(";"));
		this.name = this.sqlType.getBlockName(this);
	}

	public boolean isAllComment() {
		return StringUtils.isBlank(sql);
	}

}
