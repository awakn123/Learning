package SqlToMysql.statement;

import java.util.List;

@FunctionalInterface
public interface SqlParserService {
	List<SqlStmt> parse(String content, String name);
}
