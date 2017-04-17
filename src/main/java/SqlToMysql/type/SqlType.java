package SqlToMysql.type;

import SqlToMysql.bean.SqlBlock;
import SqlToMysql.type.oracleSqlType.OracleFunctionType;
import SqlToMysql.type.oracleSqlType.OracleProcedureType;
import SqlToMysql.type.oracleSqlType.OracleTriggerType;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlType {
	private Pattern headPattern;
	private String name;
	private int num;
	private String code;
	private Pattern endPattern;
	private Pattern namePattern;

	protected SqlType(Pattern headPattern, Pattern endPattern, Pattern namePattern, String name, String code) {
		this.headPattern = headPattern;
		this.endPattern = endPattern;
		this.name = name;
		this.code = code;
		this.namePattern = namePattern;
	}

	public Pattern getHeadPattern() {
		return headPattern;
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

	private SqlType(Pattern headPattern, String name) {
		this.headPattern = headPattern;
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
				OracleProcedureType.getInstance(),
				OracleFunctionType.getInstance(),
				OracleTriggerType.getInstance()
		);
		return types;
	}

	public static Pattern getCommentPattern() {
		return Pattern.compile("/\\*.*?\\*/");
	}

	public static String removeParam(String sql) {
		int start = -1, end = -1, count = 0;
		char[] chars = sql.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if ('(' == c) count++;
			if (')' == c) count--;
			if (count > 0 && start < 0) start = i;
			if (count == 0 && start >= 0) {
				end = i;
				break;
			}
		}
		if (start>=0 && end >=0) {
			return (start == 0 ? "" : sql.substring(0, start)) + (end + 1 == sql.length() ? "" : sql.substring(end + 1));
		}
		return sql;
	}

	public static void assignOracleBlock(List<SqlBlock> blocks) {
		blocks.stream().forEach(block -> getOracleType().stream().filter(sqlType -> sqlType.check(block)).forEach(sqlType -> {
			block.setSqlType(sqlType);
			block.splitToObject();
		}));
	}

	private boolean check(SqlBlock block) {
		Matcher m = this.headPattern.matcher(block.sql);
		return m.find();
	}

	public String getHead(SqlBlock sqlBlock) {
		Matcher m = this.headPattern.matcher(sqlBlock.sql);
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
	
}
