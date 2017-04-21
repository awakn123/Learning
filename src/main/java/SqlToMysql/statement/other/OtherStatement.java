package SqlToMysql.statement.other;

import com.google.common.collect.Lists;

import java.util.List;

public class OtherStatement {

	private List<AbstractStatementType> types;
	private String sql;

	public OtherStatement(String sql, AbstractStatementType... types) {
		this.types = Lists.newArrayList(types);
		this.sql = sql;
	}
	public OtherStatement(String sql, List<AbstractStatementType> types) {
		this.types = types;
		this.sql = sql;
	}

	public List<AbstractStatementType> getTypes() {
		return types;
	}

	public String getSql() {
		return sql;
	}

}
