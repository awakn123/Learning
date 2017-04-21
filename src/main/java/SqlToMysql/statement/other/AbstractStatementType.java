package SqlToMysql.statement.other;

public abstract class AbstractStatementType {
	private String name;
	abstract public boolean check();
	abstract boolean canToMysql();
	abstract String toMysql();
	abstract public Statement createStatement(String oracleSql);

	public AbstractStatementType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	/*ifelse("if", "then", "else"), returns("return"), begin("begin", "end"), loop("loop", "exit"),
	dbms("dbms_sql", "dbms_output"), set(":="), table("pipe"), bulk("bulk collect into"),
	other;
	private static final Logger log = LogManager.getLogger(AbstractStatementType.class);
	private List<String> keywords;

	AbstractStatementType(String... keywords) {
		this.keywords = Lists.newArrayList(keywords);
	}

	public static List<AbstractStatementType> getStatementType(String sql) {
		if (sql == null) return null;
		final String lowerSql = sql.toLowerCase();
		List<AbstractStatementType> types = Lists.newArrayList();
		for (AbstractStatementType type : AbstractStatementType.values()) {
			boolean has = type.keywords.stream().filter(key -> lowerSql.contains(key)).count() > 0;
			if (has)
				types.add(type);
		}
		if (types.isEmpty())
			return null;
		return types;
	}*/
}
