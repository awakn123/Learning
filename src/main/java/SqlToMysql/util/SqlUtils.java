package SqlToMysql.util;

import SqlToMysql.bean.SqlBlock;
import SqlToMysql.bean.SqlFile;
import SqlToMysql.type.SqlType;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlUtils {
	/**
	 * 去除评论
	 *
	 * @param sqlList
	 * @return
	 */
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

	/**
	 * 合并List，去除空格与制表符
	 *
	 * @param sqlList
	 * @return
	 */
	public static String mergeAndTrim(List<String> sqlList) {
		String sql = sqlList.stream().reduce((str, one) -> str = str + " " + one).orElse("");
		String noTab = StringUtils.replaceAll(sql, "\t", " ");
		return StringUtils.replaceAll(noTab, " +", " ");
	}

	public static Map<SqlType, List<SqlBlock>> classfiedBySqlType(List<SqlBlock> blocks) {
		Map<SqlType, List<SqlBlock>> typeToBlockMap = Maps.newHashMap();
		blocks.stream().forEach(sqlBlock -> {
			typeToBlockMap.putIfAbsent(sqlBlock.getSqlType(), new ArrayList<>());
			typeToBlockMap.get(sqlBlock.getSqlType()).add(sqlBlock);
		});
		return typeToBlockMap;
	}


	/**
	 * 将sqlfile中的一段写出到文件
	 *
	 * @param writeFilePath
	 * @param fileContent
	 * @param begin
	 * @param end
	 * @throws IOException
	 */
	public static void writeFile(String writeFilePath, SqlFile fileContent, int begin, int end) throws IOException {
		writeFileStr(writeFilePath, fileContent.getContentList().subList(begin, end));
	}

	public static void writeFile(String directoryPath, String name, List<SqlBlock> blocks) throws IOException {
		List<String> sqls = Lists.newArrayList();
		blocks.stream().forEach(sqlBlock -> {
			if (sqlBlock.isAllComment()) {
				return;
			}
			sqls.addAll(sqlBlock.getSqlList());
		});
		writeFileStr(directoryPath + "/" + name, sqls);
	}

	public static void writeFileStr(String directoryPath, String name, List<String> list) throws IOException {
		writeFileStr(directoryPath + "/" + name, list);
	}

	public static void writeFileStr(String pathStr, List<String> list) throws IOException {
		if (list == null || list.isEmpty())
			return;
		Path path = Paths.get(pathStr);
		File file = path.toFile();
		if (file.exists()) {
			file.delete();
		}
		Files.write(path, list, StandardOpenOption.CREATE_NEW);
	}
}
