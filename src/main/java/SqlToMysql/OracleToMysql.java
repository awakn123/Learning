package SqlToMysql;

import SqlToMysql.bean.OracleFunction;
import SqlToMysql.bean.OracleTrigger;
import SqlToMysql.bean.SqlBlock;
import SqlToMysql.bean.SqlFile;
import SqlToMysql.type.SqlType;
import SqlToMysql.type.oracleSqlType.OracleFunctionType;
import SqlToMysql.type.oracleSqlType.OracleTriggerType;
import SqlToMysql.util.BeanUtils;
import SqlToMysql.util.SqlUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sun.javafx.binding.StringFormatter;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static SqlToMysql.bean.SqlFile.readFile;

public class OracleToMysql {

	private static final Logger log = LogManager.getLogger();
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
		String rootPath = "./src/test/sqlWork/e8_oracle/split/31 Function structure for.sql";
		String writePath = "./src/test/sqlWork/e8_oracle/split/programDone";
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
		List<OracleFunction> functions = OracleFunctionType.getInstance().createBeanBatch(typeToBlockMap.get(OracleFunctionType.getInstance()));
		System.out.println(functions.size());
		List<String> sqls = Lists.newArrayList();
		for (OracleFunction of : functions) {
			try {
				sqls.add(OracleFunctionType.toMysqlSyntax(of));
			} catch (Exception e){
				log.error(of.getName(), e);
			}
		}
		SqlUtils.writeFileStr(writePath, "function.sql", sqls);
//		List<OracleTrigger> triggers = OracleTriggerType.getInstance().createBeanBatch(typeToBlockMap.get(OracleTriggerType.getInstance()));
//		splitTrigger(triggers);
	}


	private static void splitTrigger(List<OracleTrigger> triggers) throws IOException {
		Map<String,List<OracleTrigger>> triggerMap = BeanUtils.beanToMap(triggers, t -> t.getTime() + " "+ t.getEvent());
		List<SqlBlock> beforeInsertBlocks = Lists.newArrayList();
		List<SqlBlock> otherBlocks = Lists.newArrayList();
		List<String> autoIncreList = Lists.newArrayList("TriggerName,TableName,ColumnName");

		Map<OracleTriggerType.TriggerEnum, List<OracleTrigger>> triggerEnumMap =
				BeanUtils.beanToMap(triggerMap.get("BEFORE INSERT"), t ->	OracleTriggerType.TriggerEnum.getBySql(t.getSql()));
		triggerEnumMap.get(OracleTriggerType.TriggerEnum.autoIncrement).stream().forEach(t -> {
			autoIncreList.add(StringFormatter.format("%s,%s,%s",t.getName(), t.getTable(),OracleTriggerType.TriggerEnum.getAutoIncrementIdCol(t.getSql())).get());
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


}
