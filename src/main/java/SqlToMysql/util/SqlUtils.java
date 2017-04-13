package SqlToMysql.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SqlUtils {
	public static List<String> stripComment(List<String> sqlList) {
		if (sqlList == null || sqlList.isEmpty()) return sqlList;
		List<String> noCommentSqlList = Lists.newArrayList();
		boolean isComment = false;//当前行是否为注释
		for (int i = 0; i < sqlList.size(); i++) {
			String str = sqlList.get(i);
			if (StringUtils.isBlank(str))
				continue;
			StringBuffer preBuffer = new StringBuffer();
			while (str.indexOf("/*") >= 0 || isComment) {
				if (!isComment) {
					isComment = true;
					preBuffer.append(str, 0, str.indexOf("/*"));
				}
				int commentEnd = str.indexOf("*/");
				if (commentEnd >= 0) {
					isComment = false;
					str = str.substring(commentEnd + 2);
				} else {
					break;
				}
			}
			String sql = isComment ? preBuffer.toString() : preBuffer.append(str).toString();
			//“--”判断
			if (sql.indexOf("--") >= 0) {
				sql = sql.substring(0, sql.indexOf("--"));
			}

			if (StringUtils.isBlank(sql))
				continue;
			noCommentSqlList.add(sql);
		}
		return noCommentSqlList;
	}

	public static String mergeAndTrim(List<String> sqlList) {
		String sql = sqlList.stream().reduce((str, one) -> str = str + " " + one).orElse("");
		String noTab = StringUtils.replaceAll(sql, "\t", " ");
		return StringUtils.replaceAll(noTab, " +", " ");
	}
}
