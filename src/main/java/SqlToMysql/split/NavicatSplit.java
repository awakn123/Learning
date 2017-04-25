package SqlToMysql.split;

import SqlToMysql.bean.SqlBlock;
import SqlToMysql.bean.SqlFile;
import SqlToMysql.util.SqlUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 针对Navicat导出文件的分割方式
 * 以注释为分界线
 */
public class NavicatSplit implements SqlFileSplit {

	/**
	 * Navicat
	 * @param file
	 * @return
	 */
	@Override
	public List<SqlBlock> split(SqlFile file) {
		List<SqlBlock> blocks = Lists.newArrayList();

		Integer start = null;
		for (int i = 0; i < file.getContentList().size(); i++) {
			String str = file.getContentList().get(i);
			if (!str.startsWith("--")) continue;
			if (StringUtils.isNotBlank(str.replaceAll("-", ""))) continue;
			if (i > 0 && file.getContentList().get(i - 1).startsWith("--")) continue;
			if (start != null) {
				blocks.add(new SqlBlock(file, start, i-1));
			}
			start = i;
		}
		if (start != null && start + 1 < file.getContentList().size()) {
			blocks.add(new SqlBlock(file, start, file.getContentList().size()-1));
		}
		return blocks;
	}


	/**
	 * 根据注释分割文件。针对Navicat的导出文件。
	 * 会取出三行注释的中间一行，读取注释中除了名字之外的部分，将相同的放到同一个文件中。
	 * @param sqlFiles
	 */
	public void splitNavicatExportFile(List<SqlFile> sqlFiles, String outputDirectory) {
		if (sqlFiles == null)
			return;
		List<SqlBlock> blocks = this.split(sqlFiles);
		Map<String, List<SqlBlock>> commentMap = Maps.newHashMap();
		blocks.stream().forEach(sqlBlock -> {
			String comment;
			if (sqlBlock.getSqlList().size() <= 1) {
				return;
			}
			String str = sqlBlock.getSqlList().get(1);
			if (!str.startsWith("-- ") && str.lastIndexOf(" ") == 2)
				return;
			comment = str.substring(2, str.lastIndexOf(" "));
			if (comment != null) {
				commentMap.putIfAbsent(comment, new ArrayList<SqlBlock>());
				commentMap.get(comment).add(sqlBlock);
			}
		});
		commentMap.entrySet().stream().forEach(entry -> {
			try {
				SqlUtils.writeFile(outputDirectory, entry.getKey() + ".sql", entry.getValue());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}


	/**
	 * 读取Navicat导出文件注释中的关键字，看看它们都是什么
	 * @param sqlFiles
	 */
	public void outputSqlFileComment(List<SqlFile> sqlFiles) {
		Set<String> commentSet = Sets.newHashSet();
		for (SqlFile sqlFile : sqlFiles) {
			sqlFile.getContentList().stream().forEach(content -> {
				if (content.startsWith("--")) {
					if (StringUtils.isNotBlank(StringUtils.replaceAll(content, "-", ""))) {
						String s = content.replace("-- ", "");
						s = s.substring(0, s.lastIndexOf(" "));
						commentSet.add(s);
					}
				}
			});
		}
		commentSet.stream().forEach(s -> System.out.println(s));
	}
}
