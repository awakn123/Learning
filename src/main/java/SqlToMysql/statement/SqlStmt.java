package SqlToMysql.statement;

import SqlToMysql.util.SqlUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Function;

public class SqlStmt<T> {
	private T statement;
	private String errorMsg;

	public SqlStmt(T statement) {
		this.statement = statement;
	}
	public SqlStmt(T statement, String errorMsg) {
		this.statement = statement;
		this.errorMsg = errorMsg;
	}

	public T getStatement() {
		return statement;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void append(StringBuffer sb, Function<Appendable, SQLASTVisitor> f) {
		if (this.statement instanceof SQLStatement) {
			SQLStatement s = (SQLStatement) this.statement;
			StringBuffer sqlOut = new StringBuffer();
			s.accept(f.apply(sqlOut));
			String out = SqlUtils.mergeAndTrim(StringUtils.replaceAll(sqlOut.toString(), "\\n", " "));
			String lowerOut = out.toLowerCase();

			// 重复的;号
			out = StringUtils.replaceAll(out, ";+",";");
			//去除from dual
			int idx = lowerOut.indexOf("from dual");
			if (idx >= 0) {
				sqlOut = new StringBuffer(out);
				sqlOut.delete(idx, idx+9);
				out = sqlOut.toString();
			}
			out = StringUtils.replaceAll(out, ";", ";\n");
			out = StringUtils.replaceAll(out, "\n ","\n");//去掉句首空格
			if (out.charAt(out.length() - 2) != ';')
				sb.append(out).append(";\n");
			else
				sb.append(out);
		} else
			sb.append(statement);
	}
}
