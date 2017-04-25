package SqlToMysql;

import SqlToMysql.bean.OracleTrigger;
import SqlToMysql.bean.SqlBlock;
import SqlToMysql.bean.SqlFile;
import SqlToMysql.split.NavicatSplit;
import SqlToMysql.type.oracleSqlType.OracleTriggerType;
import SqlToMysql.util.SqlUtils;
import com.google.common.collect.Lists;
import com.sun.javafx.binding.StringFormatter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static SqlToMysql.bean.SqlFile.readFile;
import static SqlToMysql.util.MapListUtils.beanToMap;

public class SqlFileSplitMain {

	public static void main(String[] args) throws IOException {
		String rootPath = "./src/test/sqlWork/e8_oracle/split/test/FunctionChange.sql";
//		String writePath = "./src/test/sqlWork/e8_oracle/split/programDone";
		List<SqlFile> sqlFiles = readFile(rootPath);

		NavicatSplit splitService = new NavicatSplit();
		splitService.splitNavicatExportFile(sqlFiles, "./src/test/resource/e8_oracle/split");
		splitService.outputSqlFileComment(sqlFiles);
//		OracleProcedureType.classifiedByContent(blocks);
	}



	/**
	 * 分割触发器
	 * @param triggers
	 * @throws IOException
	 */
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
