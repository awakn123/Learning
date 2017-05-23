package SqlToMysql;

import SqlToMysql.bean.SqlBlock;
import SqlToMysql.bean.SqlFile;
import SqlToMysql.split.CreateSqlSplit;
import SqlToMysql.split.SqlFileSplit;
import SqlToMysql.type.SqlType;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
	public static void main(String[] args) throws IOException, SQLException {
		SqlConfig.KeepTransferCursor = false;
		SqlConfig.KeepType = true;
		SqlConfig.KeepRowtype = true;
		SqlConfig.ShowTransferCursorError = false;
		SqlConfig.ShowRowtypeError = true;
		SqlConfig.ShowTypeError = true;
		SqlConfig.showParseError = true;
		// 1~22
		String rootPath = "./src/test/sqlWork/e8_oracle/procedure/mysqldone";
		String rootPath2 = "./src/test/sqlWork/e8_oracle/procedure/procedure.sql";
		String rootPath3 = "./src/test/sqlWork/e8_oracle/procedure/procedure_unused.sql";
		SqlFileSplit sqlSplit = new CreateSqlSplit();
		String writePath = "./src/test/sqlWork/e8_oracle/procedure";

		List<SqlFile> oracleFiles = readFile(rootPath2);
		List<SqlBlock> Oracleblocks = sqlSplit.split(oracleFiles);
		Set<String> oracleNames = Sets.newHashSet();
		for (SqlBlock block: Oracleblocks) {
			if (block.getSqlType() == null) {
				SqlType.getOracleType().stream().filter(sqlType -> sqlType.check(block)).forEach(sqlType -> {
					block.setSqlType(sqlType);
					block.splitToObject();
				});
			}
			oracleNames.add(block.getName().replaceAll("\"", "").toUpperCase());
		}
		System.out.println(oracleNames.size());

		List<SqlFile> unusedOracleFiles = readFile(rootPath3);
		List<SqlBlock> unusedOracleblocks = sqlSplit.split(unusedOracleFiles);
		for (SqlBlock block: unusedOracleblocks) {
			if (block.getSqlType() == null) {
				SqlType.getOracleType().stream().filter(sqlType -> sqlType.check(block)).forEach(sqlType -> {
					block.setSqlType(sqlType);
					block.splitToObject();
				});
			}
			oracleNames.remove(block.getName().replaceAll("\"", "").toUpperCase());
		}
		System.out.println(oracleNames.size());

		// 读取并分割为sql块
		List<SqlFile> sqlFiles = readFile(rootPath);

		System.out.println(sqlFiles.size());
		List<String> sqls = Lists.newArrayList();
		sqlFiles.stream().forEach(sqlFile -> sqls.addAll(sqlFile.getContentList()));
//		SqlUtils.writeFileStr(writePath, "procedureMysqlDone.sql", sqls);
		List<SqlBlock> blocks = sqlSplit.split(sqlFiles);
		System.out.println(blocks.size());
		for (SqlBlock block: blocks) {
			if (block.getSqlType() == null) {
				SqlType.getMysqlType().stream().filter(sqlType -> sqlType.check(block)).forEach(sqlType -> {
					block.setSqlType(sqlType);
					block.splitToObject();
				});
			}
			if (oracleNames.contains(block.getName())){
				oracleNames.remove(block.getName());
			} else {
				System.out.println(block.getName());
			}
		}
		System.out.println("---");
		System.out.println(oracleNames);
//		List<OracleBean> beanList = SqlUtils.blockToOracleBean(blocks);
//		System.out.println(beanList.size());

//		SqlUtils.listToMysql(writePath, "error_temp.sql", beanList);

//		try (DruidDataSource dataSource = getMysqlDataSource()) {
//			SqlTestUtils.testProcedure(beanList, dataSource);
//		}

		System.out.println("end");
	}

}
