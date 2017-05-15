package SqlToMysql.split;

import SqlToMysql.bean.SqlBlock;
import SqlToMysql.bean.SqlFile;
import SqlToMysql.util.SqlSplitUtils;
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
		SqlSplitUtils.splitByBlockNum(blocks, num, outDir, name);
	}
}
