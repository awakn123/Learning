package SqlToMysql;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SqlFile {
	String content;
	String fileName;
	List<Integer> brlines;
	List<String> contentList;

	public SqlFile(String content, List<Integer> brlines) {
		this.content = content;
		this.brlines = brlines;
	}

	public SqlFile(List<String> contentList, String fileName) {
		this.contentList = contentList;
		this.fileName = fileName;
	}

	public List<SqlBlock> splitFileToBlock(String separator) {
		List<SqlBlock> blocks = Lists.newArrayList();

		boolean isComment = false;
		SqlBlock block = null;
		for (int i = 0; i < contentList.size(); i++) {
			String str = contentList.get(i);
			if (StringUtils.isBlank(str))
				continue;
			// 注释校验
			String preStr="";
			while (str.indexOf("/*") >= 0 || isComment) {
				if (!isComment) {
					isComment = true;
					if (!str.startsWith("/*")) {
						preStr+=str.substring(0, str.indexOf("/*"));
					}
				}
				int commentEnd = str.indexOf("*/");
				if (commentEnd >= 0) {
					isComment = false;
					str = str.substring(commentEnd + 2);
				} else {
					str = "";
					break;
				};
			}
			str = preStr + str;
			if (str.indexOf("--") >=0) {
				str = str.substring(0,str.indexOf("--"));
			}
			if (isComment || StringUtils.isBlank(str))
				continue;
			if (block == null) {
				block = new SqlBlock(i, this.fileName);
			}
			block.sql = StringUtils.isBlank(block.sql) ? str : block.sql + " " + str;
			if (str.equals(separator)) {
				block.end = i;
				blocks.add(block);
				block = null;
			}
		}
		return blocks;
	}
}
