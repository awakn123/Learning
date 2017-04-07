package SqlToMysql;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;

public class SQLServerToMysql {

	/**
	 * 读取路径的文件，如果是文件夹，则读取文件夹下的所有文件
	 *
	 * @param path
	 * @return
	 */
	public File[] readFile(String path) {
		File file = new File(path);
		if (!file.isDirectory()) {
			return new File[]{file};
		}
		return file.listFiles();
	}

	public static void main(String[] args) throws IOException {
		String rootPath = "./src/test/resource/sqlserver";
		SQLServerToMysql process = new SQLServerToMysql();
		File[] files = process.readFile(rootPath);
		checkSql(files);
	}

	/**
	 * 按SQLType中的正则检查所有Sql，并记录数量
	 *
	 * @param files
	 * @throws IOException
	 */
	private static void checkSql(File[] files) throws IOException {
		List<SqlType> sqlTypes = SqlType.getSqlType();
		List<SqlBlock> otherBlocks = Lists.newArrayList();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (i%500 == 0)
				System.out.printf("正在读取第%s个文件，共%s个，文件名:%s\n", i+1, files.length, f.getName());
			SqlFile sqlFile = readFile(f);
			String sql = mergeSpace(sqlFile.content.toUpperCase());
			for (SqlType sqlType : sqlTypes) {
				sql = countAndRemove(sqlType, sql);
			}
			sql = StringUtils.replaceAll(sql, " GO ", " GO\n");
			if (StringUtils.isNotBlank(sql)) {
				otherBlocks.add(new SqlBlock(sql, f.getName()));
			}
		}
		for (SqlType sqlType : sqlTypes) {
			if (sqlType.getNum() == 0)
				continue;
			System.out.printf("%s:%s\n", sqlType.getName(), sqlType.getNum());
		}
		if (!otherBlocks.isEmpty()) {
			System.out.println("其它:");
			for (SqlBlock sqlBlock : otherBlocks) {
				System.out.printf("%s:%s\n", sqlBlock.fileName,sqlBlock.sql);
			}
		}
	}

	/**
	 * 移除符合规则的sql，并计数
	 *
	 * @param sqlType
	 * @param content
	 * @return
	 */
	private static String countAndRemove(SqlType sqlType, String content) {
		Matcher m = sqlType.getHeadPattern().matcher(content);
		StringBuffer sb = new StringBuffer();
		int num = 0;
		while (m.find()) {
			num++;
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		sqlType.setNum(sqlType.getNum() + num);
		return mergeSpace(sb.toString());
	}

	/**
	 * trim，合并Tab与空格
	 *
	 * @param str
	 * @return
	 */
	private static String mergeSpace(String str) {
		String noTab = StringUtils.replaceAll(str, "\t", " ");
		return StringUtils.replaceAll(noTab, " +", " ");
	}

	/**
	 * 读取整个文件
	 *
	 * @param f
	 * @return
	 * @throws IOException
	 */
	private static SqlFile readFile(File f) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
			StringBuffer buffer = new StringBuffer();
			List<Integer> brlines = Lists.newArrayList();
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				if (tempString.startsWith("--")) {
					tempString = "/*" + tempString + "*/";
				}
				buffer.append(tempString).append(" ");
				brlines.add(buffer.length());
			}
			return new SqlFile(buffer.toString(), brlines);
		}
	}
}
