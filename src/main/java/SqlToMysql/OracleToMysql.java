package SqlToMysql;

import SqlToMysql.bean.OracleTrigger;
import SqlToMysql.bean.SqlBlock;
import SqlToMysql.bean.SqlFile;
import SqlToMysql.inter.TypeService;
import SqlToMysql.split.CreateSqlSplit;
import SqlToMysql.split.SqlFileSplit;
import SqlToMysql.type.SqlType;
import SqlToMysql.type.oracleSqlType.OracleTriggerType;
import SqlToMysql.util.SqlUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

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
		String rootPath = "./src/test/sqlWork/e8_oracle/split/test/trigger/trigger1_change.sql";
		String writePath = "./src/test/sqlWork/e8_oracle/split/programDone";
		TypeService typeService = OracleTriggerType.getInstance();

		// 读取并分割为sql块
		List<SqlFile> sqlFiles = readFile(rootPath);

		SqlFileSplit sqlSplit = new CreateSqlSplit();
		List<SqlBlock> blocks = sqlSplit.split(sqlFiles);
		System.out.println(blocks.size());
		// 转为OracleBlock
		SqlType.assignOracleBlock(blocks);
//		Map<SqlType, List<SqlBlock>> typeToBlockMap = SqlUtils.classfiedBySqlType(blocks);

		List<OracleTrigger> triggers = typeService.createBeanBatch(blocks);
		System.out.println(triggers.size());
//		SqlUtils.outputStatementSize(triggers, t -> t.getSqlList());

		SqlUtils.listToMysql(writePath, "trigger1.sql", triggers, OracleTriggerType.getInstance());
	}

}
