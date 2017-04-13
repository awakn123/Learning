package SqlToMysql;

import SqlToMysql.bean.OracleTrigger;
import SqlToMysql.bean.SqlBlock;
import SqlToMysql.bean.SqlFile;
import SqlToMysql.type.SqlType;
import SqlToMysql.type.oracleSqlType.OracleTriggerType;
import SqlToMysql.util.BeanUtils;
import SqlToMysql.util.CounterMap;
import SqlToMysql.util.SqlUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
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
import java.util.Set;

import static SqlToMysql.bean.SqlFile.readFile;

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
		String rootPath = "./src/test/resource/e8_oracle/split/53 Triggers structure for table.sql";
//		List<SqlFile> sqlFiles = readFile(rootPath);
//		splitFileByComment(sqlFiles);
//		getSqlFileComment(sqlFiles);
//		OracleProcedureType.classifiedByContent(blocks);

		// 读取并分析存储过程
		List<SqlFile> sqlFiles = readFile(rootPath);
		List<SqlBlock> blocks = Lists.newArrayList();
		for (SqlFile sqlFile : sqlFiles) {
			blocks.addAll(sqlFile.splitFileByComment());
		}
		SqlType.assignOracleBlock(blocks);
		Map<SqlType, List<SqlBlock>> typeToBlockMap = SqlUtils.classfiedBySqlType(blocks);
		List<OracleTrigger> triggers = OracleTriggerType.getInstance().createBeanBatch(typeToBlockMap.get(OracleTriggerType.getInstance()));
		// 统计聚合值
		CounterMap<String> counter = new CounterMap<>();
		triggers.forEach(t -> counter.putOrIncrement(t.getTime() + " " + t.getEvent()));
		counter.outputToConsole();

		Map<String,List<OracleTrigger>> triggerMap = BeanUtils.beanToMap(triggers, t -> t.getTime() + " "+ t.getEvent());
		System.out.println(triggerMap.get("BEFORE INSERT").get(0).getBlock().getSqlList());
	}


	private static void splitFileByComment(List<SqlFile> sqlFiles) {
		List<SqlBlock> blocks = sqlFiles.get(0).splitFileByComment();
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
				writeFile("./src/test/resource/e8_oracle/split", entry.getKey() + ".sql", entry.getValue());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	private static void writeFile(String pathStr, String name, List<SqlBlock> blocks) throws IOException {
		Path path = Paths.get(pathStr + "/" + name);
		File file = path.toFile();
		if (file.exists()) {
			file.delete();
		}
		List<String> sqls = Lists.newArrayList();
		blocks.stream().forEach(sqlBlock -> {
			if (sqlBlock.isAllComment()) {
				return;
			}
			sqls.addAll(sqlBlock.getSqlList());
		});
		Files.write(path, sqls, StandardOpenOption.CREATE_NEW);
	}

	private static void getSqlFileComment(List<SqlFile> sqlFiles) {
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

	private static void writeFile(String writeFilePath, SqlFile fileContent, int begin, int end) throws IOException {
		Path path = Paths.get(writeFilePath);
		File file = path.toFile();
		if (file.exists()) {
			file.delete();
		}
		Files.write(path, fileContent.getContentList().subList(begin, end), StandardOpenOption.CREATE_NEW);
	}


}
