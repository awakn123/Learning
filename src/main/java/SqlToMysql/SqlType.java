package SqlToMysql;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.regex.Pattern;

public class SqlType {
	private Pattern pattern;
	private String name;
	private int num;

	public Pattern getPattern() {
		return pattern;
	}

	public String getName() {
		return name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	private SqlType(Pattern pattern, String name) {
		this.pattern = pattern;
		this.name = name;
	}
	public static List<SqlType> getSqlType() {
		List<SqlType> types = Lists.newArrayList(
				new SqlType(Pattern.compile("CREATE PROCEDURE (((?! GO ).)*)?( GO ){1}"), "创建存储过程"),
				new SqlType(Pattern.compile("ALTER (PROCEDURE|PROC) (((?! GO ).)*)?( GO ){1}"), "修改存储过程"),
				new SqlType(Pattern.compile("DROP (PROCEDURE|PROC) (((?! GO ).)*)?( GO ){1}"),"删除存储过程"),

				new SqlType(Pattern.compile("CREATE TRIGGER (((?! GO ).)*)?( GO ){1}"), "创建触发器"),
				new SqlType(Pattern.compile("ALTER TRIGGER (((?! GO ).)*)?( GO ){1}"), "变更触发器"),
				new SqlType(Pattern.compile("DROP TRIGGER (((?! GO ).)*)?( GO ){1}"),"删除触发器"),

				new SqlType(Pattern.compile("CREATE VIEW (((?! GO ).)*)?( GO ){1}"), "创建视图"),
				new SqlType(Pattern.compile("DROP VIEW (((?! GO ).)*)?( GO ){1}"), "删除视图"),

				new SqlType(Pattern.compile("CREATE FUNCTION (((?! GO ).)*)?( GO ){1}"), "创建函数"),
				new SqlType(Pattern.compile("ALTER FUNCTION (((?! GO ).)*)?( GO ){1}"), "更新函数"),
				new SqlType(Pattern.compile("DROP FUNCTION (((?! GO ).)*)?( GO ){1}"), "删除函数"),

				new SqlType(Pattern.compile("(EXECUTE|EXEC) (((?! GO ).)*)?( GO ){1}"), "EXECUTE|EXEC"),

				new SqlType(Pattern.compile("CREATE TABLE (((?! GO ).)*)?( GO ){1}"),"建表"),
				new SqlType(Pattern.compile("DROP TABLE (((?! GO ).)*)?( GO ){1}"),"删除表"),

				new SqlType(Pattern.compile("ALTER TABLE .*? ADD PRIMARY KEY .*?( GO ){1}"), "设置主键"),
				new SqlType(Pattern.compile("ALTER TABLE .*? ADD CONSTRAINT .*?( GO ){1}"), "默认值"),
				new SqlType(Pattern.compile("ALTER TABLE .*? ADD (((?! GO ).)*)?( GO ){1}"), "增加表字段"),
				new SqlType(Pattern.compile("ALTER TABLE .*? ALTER (((?! GO ).)*)?( GO ){1}"), "变更表字段"),
				new SqlType(Pattern.compile("ALTER TABLE .*? DROP (((?! GO ).)*)?( GO ){1}"), "删除表字段"),

				new SqlType(Pattern.compile("CREATE CLUSTERED INDEX .*?( GO ){1}"),"创建聚集索引"),
				new SqlType(Pattern.compile("CREATE NONCLUSTERED INDEX .*?( GO ){1}"), "创建非聚集索引"),
				new SqlType(Pattern.compile("CREATE INDEX .*?( GO ){1}"), "创建索引"),
				new SqlType(Pattern.compile("DROP INDEX .*?( GO ){1}"), "删除索引"),

				new SqlType(Pattern.compile("INSERT( INTO)? (((?! GO ).)*)?( GO ){1}"), "新增记录"),
				new SqlType(Pattern.compile("SELECT (((?! GO ).)*)? INTO (((?! GO ).)*)?( GO ){1}"), "SELECT INTO新增记录"),
				new SqlType(Pattern.compile("DELETE( FROM)? (((?! GO ).)*)?( GO ){1}"), "删除记录"),
				new SqlType(Pattern.compile("UPDATE .*? SET (((?! GO ).)*)?( GO ){1}"), "更新记录"),

				new SqlType(Pattern.compile("TRUNCATE (((?! GO ).)*)?( GO ){1}"), "TRUNCATE"),
				new SqlType(Pattern.compile("SET (((?! GO ).)*)?( GO ){1}"), "SET"),

				new SqlType(Pattern.compile("\\/\\*.*?\\*\\/"), "注释")
		);
		return types;
	}
}
