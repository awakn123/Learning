package SqlToMysql.type;

import SqlToMysql.bean.OracleBean;
import SqlToMysql.bean.SqlBlock;
import SqlToMysql.statement.DruidSqlParser;
import SqlToMysql.statement.O2MVisitor;
import SqlToMysql.statement.SqlParserService;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Function;

public interface TypeService<T extends OracleBean> {
	default T createBean(SqlBlock block) {
		return this.createBean(block, DruidSqlParser::parse);
	};
	T createBean(SqlBlock block, SqlParserService parser);
	default List<T> createBeanBatch(List<SqlBlock> blocks) {
		if (blocks == null)
			return null;
		List<T> list = Lists.newArrayList();
		blocks.forEach(block -> list.add(this.createBean(block)));
		return list;
	};

	default String toMysqlSyntax(T t) {
		return this.toMysqlSyntax(t, appendable -> new O2MVisitor(appendable, false));
	};

	String toMysqlSyntax(T t, Function<Appendable, SQLASTVisitor> f);
}
