package SqlToMysql.statement.other;

import com.google.common.collect.Lists;

import java.util.List;

public class OtherStatementVisitor {

	private List<String> sqls = Lists.newArrayList();

	public List<String> getSqls() {
		return sqls;
	}

	public void visit(OtherStatement other) {
		sqls.add(other.toString());
	}

}
