package SqlToMysql.split;

import SqlToMysql.bean.SqlBlock;
import SqlToMysql.bean.SqlFile;
import SqlToMysql.util.SqlUtils;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.List;

public interface SqlFileSplit {

	List<SqlBlock> split(SqlFile file);

	default List<SqlBlock> split(List<SqlFile> files) {
		if (files == null || files.isEmpty()) return null;
		List<SqlBlock> blocks = Lists.newArrayList();
		files.stream().forEach(file-> blocks.addAll(this.split(file)));
		return blocks;
	}

	default void splitFileByNum(List<SqlFile> files, int num, String outDir, String name) throws IOException {
		List<SqlBlock> blocks = this.split(files);
		this.splitByBlockNum(blocks, num, outDir, name);
	}

	default void splitByBlockNum(List<SqlBlock> blocks, int num, String outDir, String name) throws IOException {
		if (num <= 0) return;
		if (blocks == null || blocks.isEmpty()) return;
		int start = 0;
		int end = num;
		int i=0;
		while (true) {
			i++;
			if (blocks.size() >= end) {
				SqlUtils.writeFile(outDir, name + i + ".sql", blocks.subList(start, end));
			} else {
				SqlUtils.writeFile(outDir, name + i + ".sql", blocks.subList(start, blocks.size()));
				break;
			}
			start = end;
			end += num;
		}
	}
}
