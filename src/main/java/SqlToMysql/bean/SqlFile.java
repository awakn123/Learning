package SqlToMysql.bean;

import com.google.common.collect.Lists;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class SqlFile {
	private String fileName;
	private List<String> contentList;

	private SqlFile(List<String> contentList, String fileName) {
		this.contentList = contentList;
		this.fileName = fileName;
	}

	public static List<SqlFile> readFile(String filePath) throws IOException {
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

	public String getFileName() {
		return fileName;
	}

	public List<String> getContentList() {
		return contentList;
	}

}
