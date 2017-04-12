package SqlToMysql;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

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

	public List<SqlBlock> splitFileByComment() {
		List<SqlBlock> blocks = Lists.newArrayList();

		Integer start = null;
		for (int i = 0; i < contentList.size(); i++) {
			String str = contentList.get(i);
			if (!str.startsWith("--")) continue;
			if (StringUtils.isNotBlank(str.replaceAll("-", ""))) continue;
			if (!contentList.get(i - 1).startsWith("--")) {
				if (start != null) {
					blocks.add(new SqlBlock(this, start, i-1));
				}
				start = i;
			}
		}
		if (start != null && start + 1 < contentList.size()) {
			blocks.add(new SqlBlock(this, start, contentList.size()-1));
		}
		return blocks;
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
			String preStr = "";
			while (str.indexOf("") >= 0 || isComment) {
				if (!isComment) {
					isComment = true;
					if (!str.startsWith("")) {
						preStr += str.substring(0, str.indexOf(""));
					}
				}
				int commentEnd = str.indexOf("");
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
				block = new SqlBlock(i, this);
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
