package SqlToMysql;

import java.util.List;

public class SqlFile {
	String content;
	List<Integer> brlines;
	public SqlFile(String content, List<Integer> brlines){
		this.content = content;
		this.brlines = brlines;
	}
}
