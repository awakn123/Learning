package SqlToMysql;

import SqlToMysql.bean.OracleBean;
import SqlToMysql.bean.SqlBlock;
import SqlToMysql.bean.SqlFile;
import SqlToMysql.split.CreateSqlSplit;
import SqlToMysql.split.SqlFileSplit;
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
		SqlConfig.KeepTransferCursor = false;
		SqlConfig.KeepType = true;
		SqlConfig.KeepRowtype = true;
		SqlConfig.ShowTransferCursorError = false;
		SqlConfig.ShowRowtypeError = true;
		SqlConfig.ShowTypeError = true;
		SqlConfig.showParseError = true;
		// 1~22
		String rootPath = "./src/test/sqlWork/e8_oracle/split/programDone/procedure/one/procedure_OracleExprStatement(689)_change.sql";
		String writePath = "./src/test/sqlWork/e8_oracle/split/programDone/procedure/mysql/one";

		// 读取并分割为sql块
		List<SqlFile> sqlFiles = readFile(rootPath);

		SqlFileSplit sqlSplit = new CreateSqlSplit();
		List<SqlBlock> blocks = sqlSplit.split(sqlFiles);
		System.out.println(blocks.size());
		List<OracleBean> beanList = SqlUtils.blockToBean(blocks);
		System.out.println(beanList.size());

//		beanList.stream().filter(t->t.getParams().stream().filter(op -> DataTypeConvert.ORACLE_TRANSFER_CURSOR.equals(op.getType())).count() == 0).forEach(t-> System.out.println(t));
//		SqlUtils.splitByFirstSqlType(writePath, "procedure_%s.sql", beanList);

		SqlUtils.listToMysql(writePath, "procedure_OracleExprStatement(689).sql", beanList);
	}

}
