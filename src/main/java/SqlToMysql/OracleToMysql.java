package SqlToMysql;

import SqlToMysql.bean.OracleFunction;
import SqlToMysql.bean.SqlBlock;
import SqlToMysql.bean.SqlFile;
import SqlToMysql.statement.O2MVisitor;
import SqlToMysql.statement.SqlStmt;
import SqlToMysql.type.SqlType;
import SqlToMysql.type.oracleSqlType.OracleFunctionType;
import SqlToMysql.util.MapListUtils;
import SqlToMysql.util.SqlUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
		String rootPath = "./src/test/sqlWork/e8_oracle/split/test/FunctionChange.sql";
		String writePath = "./src/test/sqlWork/e8_oracle/split/programDone";
		// 读取并分割为sql块
		List<SqlFile> sqlFiles = readFile(rootPath);
		List<SqlBlock> blocks = Lists.newArrayList();
		for (SqlFile sqlFile : sqlFiles) {
			blocks.addAll(sqlFile.splitFileByComment());
		}
		// 转为OracleBlock
		SqlType.assignOracleBlock(blocks);
		Map<SqlType, List<SqlBlock>> typeToBlockMap = SqlUtils.classfiedBySqlType(blocks);

		// 将FunctionType类型的Block转为OracleFunction
		List<OracleFunction> functions = OracleFunctionType.getInstance().createBeanBatch(typeToBlockMap.get(OracleFunctionType.getInstance()));
		System.out.println(functions.size());

		// 输出function中各statement的量
//		outputFunctionStatementSize(functions);

		List<String> sqls = Lists.newArrayList();
		System.out.println("------------------------------------------------------------------------------");
		for (OracleFunction of : functions) {
			try {
				sqls.add(OracleFunctionType.toMysqlSyntax(of));
			} catch (Exception e){
				log.error(of.getName(), e);
			}
		}
		System.out.println("------------------------------------------------------------------------------");
		// 输出Visitor中的统计
		O2MVisitor.counter.outputToConsole();
		System.out.println(O2MVisitor.counter.size());
		System.out.println(MapListUtils.toOutput(O2MVisitor.errorMsgs));

		// 写出到文件
		SqlUtils.writeFileStr(writePath, "function.sql", sqls);
	}

	/**
	 * 读取OracleFunction中的Statement，判断组合并输出各statement类型的数量
	 * @param functions
	 */
	private static void outputFunctionStatementSize(List<OracleFunction> functions) {
		Map<String, List<SqlStmt>> map = Maps.newHashMap();
		functions.stream().forEach(func -> {
			if (func.getSqlList() == null) {
				System.out.println("null:" + func.getName());
				return;
			}
			Map<String, List<SqlStmt>> m = MapListUtils.beanToMap(func.getSqlList(), sqlStmt -> {
				if (sqlStmt == null || sqlStmt.getStatement() == null) return "null";
				SqlStmt s = (SqlStmt) sqlStmt;
				if (s.getStatement() instanceof SQLStatement) {
					SQLStatement stmt = (SQLStatement) s.getStatement();
					return stmt.getClass().getName();
				} else {
					System.out.println("error:"+s.getErrorMsg());
					return "string";
				}
			});
			MapListUtils.reduce(map, m);
		});
		System.out.println(MapListUtils.toSizeOutput(map));
	}


}
