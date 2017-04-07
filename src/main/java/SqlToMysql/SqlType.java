package SqlToMysql;

import SqlToMysql.oracleSqlType.OracleProcedure;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlType {
	private Pattern pattern;
	private String name;
	private int num;
	private String code;
	private Pattern endPattern;
	private Pattern namePattern;

	public SqlType(Pattern pattern, Pattern endPattern, Pattern namePattern, String name, String code) {
		this.pattern = pattern;
		this.endPattern = endPattern;
		this.name = name;
		this.code = code;
		this.namePattern = namePattern;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public Pattern getEndPattern() {
		return endPattern;
	}

	public String getContent(SqlBlock block) {
		return block.sql;
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

	public String getCode() {
		return code;
	}

	private SqlType(Pattern pattern, String name) {
		this.pattern = pattern;
		this.name = name;
	}

	public static List<SqlType> getSqlType() {
		List<SqlType> types = Lists.newArrayList(
				new SqlType(Pattern.compile("CREATE PROCEDURE (((?! GO ).)*)?( GO ){1}"), "创建存储过程"),
				new SqlType(Pattern.compile("ALTER (PROCEDURE|PROC) (((?! GO ).)*)?( GO ){1}"), "修改存储过程"),
				new SqlType(Pattern.compile("DROP (PROCEDURE|PROC) (((?! GO ).)*)?( GO ){1}"), "删除存储过程"),

				new SqlType(Pattern.compile("CREATE TRIGGER (((?! GO ).)*)?( GO ){1}"), "创建触发器"),
				new SqlType(Pattern.compile("ALTER TRIGGER (((?! GO ).)*)?( GO ){1}"), "变更触发器"),
				new SqlType(Pattern.compile("DROP TRIGGER (((?! GO ).)*)?( GO ){1}"), "删除触发器"),

				new SqlType(Pattern.compile("CREATE VIEW (((?! GO ).)*)?( GO ){1}"), "创建视图"),
				new SqlType(Pattern.compile("DROP VIEW (((?! GO ).)*)?( GO ){1}"), "删除视图"),

				new SqlType(Pattern.compile("CREATE FUNCTION (((?! GO ).)*)?( GO ){1}"), "创建函数"),
				new SqlType(Pattern.compile("ALTER FUNCTION (((?! GO ).)*)?( GO ){1}"), "更新函数"),
				new SqlType(Pattern.compile("DROP FUNCTION (((?! GO ).)*)?( GO ){1}"), "删除函数"),

				new SqlType(Pattern.compile("(EXECUTE|EXEC) (((?! GO ).)*)?( GO ){1}"), "EXECUTE|EXEC"),

				new SqlType(Pattern.compile("CREATE TABLE (((?! GO ).)*)?( GO ){1}"), "建表"),
				new SqlType(Pattern.compile("DROP TABLE (((?! GO ).)*)?( GO ){1}"), "删除表"),

				new SqlType(Pattern.compile("ALTER TABLE .*? ADD PRIMARY KEY .*?( GO ){1}"), "设置主键"),
				new SqlType(Pattern.compile("ALTER TABLE .*? ADD CONSTRAINT .*?( GO ){1}"), "默认值"),
				new SqlType(Pattern.compile("ALTER TABLE .*? ADD (((?! GO ).)*)?( GO ){1}"), "增加表字段"),
				new SqlType(Pattern.compile("ALTER TABLE .*? ALTER (((?! GO ).)*)?( GO ){1}"), "变更表字段"),
				new SqlType(Pattern.compile("ALTER TABLE .*? DROP (((?! GO ).)*)?( GO ){1}"), "删除表字段"),

				new SqlType(Pattern.compile("CREATE CLUSTERED INDEX .*?( GO ){1}"), "创建聚集索引"),
				new SqlType(Pattern.compile("CREATE NONCLUSTERED INDEX .*?( GO ){1}"), "创建非聚集索引"),
				new SqlType(Pattern.compile("CREATE INDEX .*?( GO ){1}"), "创建索引"),
				new SqlType(Pattern.compile("DROP INDEX .*?( GO ){1}"), "删除索引"),

				new SqlType(Pattern.compile("INSERT( INTO)? (((?! GO ).)*)?( GO ){1}"), "新增记录"),
				new SqlType(Pattern.compile("SELECT (((?! GO ).)*)? INTO (((?! GO ).)*)?( GO ){1}"), "SELECT INTO新增记录"),
				new SqlType(Pattern.compile("DELETE( FROM)? (((?! GO ).)*)?( GO ){1}"), "删除记录"),
				new SqlType(Pattern.compile("UPDATE .*? SET (((?! GO ).)*)?( GO ){1}"), "更新记录"),

				new SqlType(Pattern.compile("TRUNCATE (((?! GO ).)*)?( GO ){1}"), "TRUNCATE"),
				new SqlType(Pattern.compile("SET (((?! GO ).)*)?( GO ){1}"), "SET"),

				new SqlType(getCommentPattern(), "注释")
		);
		return types;
	}

	public static List<SqlType> getOracleType() {
		List<SqlType> types = Lists.newArrayList(
				new OracleProcedure()
		);
		return types;
	}
	public static Pattern getCommentPattern() {
		return Pattern.compile("/\\*.*?\\*/");
	}

	public static void assignOracleBlock(List<SqlBlock> blocks) {
		blocks.stream().forEach(block -> getOracleType().stream().filter(sqlType -> sqlType.check(block)).forEach(sqlType -> block.setSqlType(sqlType)));
	}

	private boolean check(SqlBlock block) {
		Matcher m = this.pattern.matcher(block.sql);
		return m.find();
	}

	public String getHead(SqlBlock sqlBlock) {
		Matcher m = this.pattern.matcher(sqlBlock.sql);
		return m.find() ? m.group() : null;
	}


	public String getBlockName(SqlBlock sqlBlock) {
		String head = getHead(sqlBlock);
		if (head == null) {
			return null;
		}
		Matcher m = this.namePattern.matcher(head);
		return m.find() ? m.group() : null;
	}

	public SingleType getBlockType(SqlBlock sqlBlock) {
		if (sqlBlock == null || sqlBlock.sqlList == null || sqlBlock.sqlList.isEmpty()) {
			return SingleType.empty;
		}
		if (sqlBlock.sqlList.size() > 2) {
			return SingleType.many;
		}
		String sql = sqlBlock.sqlList.get(0);
		return Arrays.stream(SingleType.values()).filter(singleType -> singleType.check(sql)).findFirst().orElse(SingleType.other);
	}

	public enum SingleType {
		select("^SELECT"), selectinto("^SELECT INTO"), cursor("^OPEN"),
		update("^UPDATE"), insert("^INSERT"), delete("^DELETE"),
		many, empty, other;
		private Pattern pattern;

		private SingleType() {
		}

		private SingleType(String p) {
			this.pattern = Pattern.compile(p, Pattern.CASE_INSENSITIVE);
		}

		public boolean check(String sql) {
			if (pattern == null)
				return false;
			Matcher m = this.pattern.matcher(sql);
			return m.find();
		}
	}
}
