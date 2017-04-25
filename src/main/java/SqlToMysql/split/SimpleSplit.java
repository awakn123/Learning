package SqlToMysql.split;

import SqlToMysql.bean.SqlBlock;
import SqlToMysql.bean.SqlFile;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 简单的分割，以结束符号为分割依据。
 */
public class SimpleSplit implements SqlFileSplit {
	public List<SqlBlock> split(SqlFile file) {
		return this.split(file, "/");
	}

	public List<SqlBlock> split(SqlFile file, String separator) {
		List<SqlBlock> blocks = Lists.newArrayList();

		boolean isComment = false;
		SqlBlock block = null;
		for (int i = 0; i < file.getContentList().size(); i++) {
			String str = file.getContentList().get(i);
			if (StringUtils.isBlank(str))
				continue;
			// 注释校验
			String preStr = "";
			while (str.indexOf("") >= 0 || isComment) {
				if (!isComment) {
					isComment = true;
					if (!str.startsWith("")) {
						preStr += str.substring(0, str.indexOf(""));
					}
				}
				int commentEnd = str.indexOf("*/");
				if (commentEnd >= 0) {
					isComment = false;
					str = str.substring(commentEnd + 2);
				} else {
					str = "";
					break;
				}
				;
			}
			str = preStr + str;
			if (str.indexOf("--") >= 0) {
				str = str.substring(0, str.indexOf("--"));
			}
			if (isComment || StringUtils.isBlank(str))
				continue;
			if (block == null) {
				block = new SqlBlock(i, file);
			}
			block.sql = StringUtils.isBlank(block.sql) ? str : block.sql + " " + str;
			if (str.equals(separator)) {
				block.setEnd(i);
				blocks.add(block);
				block = null;
			}
		}
		return blocks;
	}
}
