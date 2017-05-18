package SqlToMysql.util;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ListUtils {
	public static String toString(List list) {
		return toString(list, ",");
	}

	public static String toString(List list, String separator) {
		if (list == null || list.isEmpty())
			return null;
		StringBuffer sb = new StringBuffer();
		for (Object o : list) {
			sb.append(o.toString());
			sb.append(separator);
		}
		if (StringUtils.isBlank(separator))
			return sb.toString();
		return sb.substring(0, sb.length() - separator.length());
	}
}
