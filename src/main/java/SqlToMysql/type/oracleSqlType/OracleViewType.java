package SqlToMysql.type.oracleSqlType;

import SqlToMysql.bean.OracleView;
import SqlToMysql.bean.SqlBlock;
import SqlToMysql.statement.SqlParserService;
import SqlToMysql.statement.SqlStmt;
import SqlToMysql.type.SqlType;
import SqlToMysql.type.TypeService;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OracleViewType extends SqlType implements TypeService<OracleView> {

	private static class OracleViewTypeHolder {
		private static final OracleViewType instance = new OracleViewType();
	}

	private OracleViewType() {
		super(Pattern.compile("^CREATE OR REPLACE FORCE VIEW \"\\w+\" AS", Pattern.CASE_INSENSITIVE),
				Pattern.compile(" +;$", Pattern.CASE_INSENSITIVE),
				Pattern.compile("\"\\w+\""),
				"创建视图", "view");
	}

	public static final OracleViewType getInstance() {
		return OracleViewTypeHolder.instance;
	}

	@Override
	public OracleView createBean(SqlBlock block, SqlParserService parser) {
		String sql = block.sql;
		String name = this.getBlockName(block);
		name = name.replaceAll("\"", "");
		Matcher m = this.getHeadPattern().matcher(sql);
		sql = m.replaceAll("").trim();
		List<SqlStmt> sqlList = parser.parse(sql, name);
		return new OracleView(name, sql, block, sqlList);
	}

	@Override
	public String toMysqlSyntax(OracleView oracleView, Function<Appendable, SQLASTVisitor> f) {
		StringBuilder sb = new StringBuilder();
		sb.append("-- -------------------------------------------------------------------------------------------\n");
		sb.append("-- ").append(oracleView.getName()).append("\n");
		sb.append("-- -------------------------------------------------------------------------------------------\n");
		StringBuilder contentOut = new StringBuilder();
		oracleView.getSqlList().stream().forEach(sql -> sql.append(contentOut, f));
		sb.append("CREATE OR REPLACE VIEW  ").append(oracleView.getName()).append(" AS\n");
		sb.append(contentOut);
		return sb.toString();
	}
}
