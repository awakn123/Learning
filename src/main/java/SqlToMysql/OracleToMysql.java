package SqlToMysql;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OracleToMysql {

	/**
	 * SQL处理流程：
	 * 读取SQL文件,按行数对应代码组成List
	 * 分割代码块、记录代码块对应行数，注意不要把注释也算在内了。
	 * 将代码块与SQL类型对应，如创建存储过程、创建索引，暂时来说，一个代码块只对应一种类型
	 * 分析各类型，并根据类型修改代码块的内容
	 * 修改代码块对应位置的字符串
	 * 将数组写入新文件
	 *
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// 1~22
		String rootPath = "./src/test/resource/oracle";
		List<SqlFile> sqlFiles = readFile(rootPath);
		List<SqlBlock> blocks = Lists.newArrayList();
		for (SqlFile sqlFile : sqlFiles) {
			blocks.addAll(sqlFile.splitFileToBlock("/"));
		}
		SqlType.assignOracleBlock(blocks);
		blocks.stream().forEach(sqlBlock -> sqlBlock.splitToObject());
		Map<SqlType.SingleType, Integer> blockNumMap = Maps.newHashMap();
		Map<SqlType.SingleType, List<SqlBlock>> blockMap = Maps.newHashMap();
		blocks.stream().forEach(sqlBlock -> {
			int i = blockNumMap.getOrDefault(sqlBlock.type, 0);
			blockNumMap.put(sqlBlock.type, i + 1);
			blockMap.putIfAbsent(sqlBlock.type, new ArrayList<SqlBlock>());
			blockMap.get(sqlBlock.type).add(sqlBlock);
		});
		blockNumMap.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + ":" + entry.getValue()));
		System.out.println(blocks.size());
	}

	private static void writeFile(String writeFilePath, SqlFile fileContent, int begin, int end) throws IOException {
		Path path = Paths.get(writeFilePath);
		File file = path.toFile();
		if (file.exists()) {
			file.delete();
		}
		Files.write(path, fileContent.contentList.subList(begin, end), StandardOpenOption.CREATE_NEW);
	}


	private static List<SqlFile> readFile(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		File file = path.toFile();
		if (!file.exists()) {
			return null;
		}
		List<SqlFile> sqlFiles = Lists.newArrayList();
		if (!file.isDirectory()) {
			List<String> contentList = Files.readAllLines(path);
			sqlFiles.add(new SqlFile(contentList, file.getName()));
			return sqlFiles;
		}
		Arrays.stream(file.listFiles()).forEach(f -> {
			List<String> contentList = null;
			try {
				contentList = Files.readAllLines(f.toPath());
			} catch (IOException e) {
				System.err.printf("读取文件出错%s\n", f.toPath());
			}
			sqlFiles.add(new SqlFile(contentList, f.getName()));
		});
		return sqlFiles;
	}
}
