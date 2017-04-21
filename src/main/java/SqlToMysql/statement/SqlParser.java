package SqlToMysql.statement;

import SqlToMysql.statement.other.AbstractStatementType;
import SqlToMysql.statement.other.Statement;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.util.JdbcConstants;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public final class SqlParser {
	static final List<AbstractStatementType> astlist = Lists.newArrayList();
	private static final Logger log = LogManager.getLogger(SqlParser.class);

	public static List<SqlStmt> parse(String content, String name) {
		List<String> sqlList = Lists.newArrayList(content.split(";"));
		List<SqlStmt> stmts = Lists.newArrayList();
		String temp = null;
		for (int i = 0; i < sqlList.size(); i++) {
			String sql = sqlList.get(i);
			if (StringUtils.isBlank(sql)) continue;
			sql = temp == null ? (sql + ";") : (temp + sql + ";");
			try {
				SQLStatement statement = parseByDruid(sql);
				SqlStmt stmt = new SqlStmt(statement);
				temp = null;
				stmts.add(stmt);
			} catch (Exception e) {
				String lowerSql = sql.toLowerCase().trim();
				if (temp != null || e.getMessage().contains("expect END")) {
					temp = sql;
				}
				else if (e.getMessage().contains("TODO")) {
					stmts.add(new SqlStmt(sql, e.getMessage()));
					log.error("name:" + name + ", message:" + e.getMessage() + ", sql:" + sql);
				}
				else if (lowerSql.startsWith("end"))
					stmts.add(new SqlStmt(sql));
				else {
					stmts.add(new SqlStmt(sql));
					log.error("name:" + name + ", sql:" + sql, e);
				}
			}
		}
		;
		return stmts;
	}

	public static Statement parseBySelf(String sql) {
		for (AbstractStatementType ast : astlist) {
			if (ast.check()) {
				return ast.createStatement(sql);
			}
		}
		log.error("没有对应的模式,sql:" + sql);
		return null;
	}

	public static SQLStatement parseByDruid(String sql) {
		SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, JdbcConstants.ORACLE);
		List<SQLStatement> statementList = new ArrayList<SQLStatement>();
		parser.parseStatementList(statementList, 1);
		return statementList.get(0);
	}
	public static SQLStatement parseByDruidMysql(String sql) {
		SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, JdbcConstants.MYSQL);
		List<SQLStatement> statementList = new ArrayList<SQLStatement>();
		parser.parseStatementList(statementList, 1);
		return statementList.get(0);
	}
}
