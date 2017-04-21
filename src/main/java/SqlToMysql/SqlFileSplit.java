package SqlToMysql;

import SqlToMysql.bean.OracleTrigger;
import SqlToMysql.bean.SqlBlock;
import SqlToMysql.bean.SqlFile;
import SqlToMysql.type.oracleSqlType.OracleTriggerType;
import SqlToMysql.util.SqlUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sun.javafx.binding.StringFormatter;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static SqlToMysql.bean.SqlFile.readFile;
import static SqlToMysql.util.MapListUtils.beanToMap;

public class SqlFileSplit {

	public static void main(String[] args) throws IOException {
		String rootPath = "./src/test/sqlWork/e8_oracle/split/test/FunctionChange.sql";
		String writePath = "./src/test/sqlWork/e8_oracle/split/programDone";
//		List<SqlFile> sqlFiles = readFile(rootPath);
//		splitFileByComment(sqlFiles);
//		getSqlFileComment(sqlFiles);
//		OracleProcedureType.classifiedByContent(blocks);

		// 读取并分割为块
		List<SqlFile> sqlFiles = readFile(rootPath);
		List<SqlBlock> blocks = Lists.newArrayList();
		for (SqlFile sqlFile : sqlFiles) {
			blocks.addAll(sqlFile.splitFileByComment());
		}
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
				SqlUtils.writeFile("./src/test/resource/e8_oracle/split", entry.getKey() + ".sql", entry.getValue());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
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

	private static void splitTrigger(List<OracleTrigger> triggers) throws IOException {
		Map<String, List<OracleTrigger>> triggerMap = beanToMap(triggers, t -> t.getTime() + " " + t.getEvent());
		List<SqlBlock> beforeInsertBlocks = Lists.newArrayList();
		List<SqlBlock> otherBlocks = Lists.newArrayList();
		List<String> autoIncreList = Lists.newArrayList("TriggerName,TableName,ColumnName");

		Map<OracleTriggerType.TriggerEnum, List<OracleTrigger>> triggerEnumMap =
				beanToMap(triggerMap.get("BEFORE INSERT"), t -> OracleTriggerType.TriggerEnum.getBySql(t.getSql()));
		triggerEnumMap.get(OracleTriggerType.TriggerEnum.autoIncrement).stream().forEach(t -> {
			autoIncreList.add(StringFormatter.format("%s,%s,%s", t.getName(), t.getTable(), OracleTriggerType.TriggerEnum.getAutoIncrementIdCol(t.getSql())).get());
		});
		triggerEnumMap.get(OracleTriggerType.TriggerEnum.other).stream().forEach(t -> {
			beforeInsertBlocks.add(t.getBlock());
		});
		triggerMap.entrySet().stream().forEach(entry -> {
			if ("BEFORE INSERT".equals(entry.getKey()) || entry.getValue() == null) {
				return;
			}
			entry.getValue().stream().forEach(t -> otherBlocks.add(t.getBlock()));
		});

		System.out.println(beforeInsertBlocks.size());
		System.out.println(otherBlocks.size());
		System.out.println(autoIncreList.size());
		SqlUtils.writeFile("./src/test/resource/e8_oracle/split/trigger", "beforeInsert.sql", beforeInsertBlocks);
		SqlUtils.writeFile("./src/test/resource/e8_oracle/split/trigger", "other.sql", otherBlocks);
		SqlUtils.writeFileStr("./src/test/resource/e8_oracle/split/trigger", "autoIncre.txt", autoIncreList);
//		triggerEnumMap.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + ":" + entry.getValue().size()));
		/*triggerEnumMap.get(OracleTriggerType.TriggerEnum.autoIncrement).stream().forEach(
				t -> System.out.printf("(Table_Name=\'%s\' and column_Name=\'%s\') or\n",
//						t.getName(),
						t.getTable(),
//						t.getSql()));
						OracleTriggerType.TriggerEnum.getAutoIncrementIdCol(t.getSql())));*/
	}
}
