package SqlToMysql.util;

import SqlToMysql.bean.OracleBean;
import SqlToMysql.bean.SqlBlock;
import SqlToMysql.statement.SqlStmt;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.oracle.ast.stmt.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlSplitUtils {

	private static final Logger log = LogManager.getLogger();

	/**
	 * 会将if与for、loop、exception、fetch类型的放在complex下，其它类型的放在simple下
	 *
	 * @param writePath
	 * @param beanList
	 */
	public static void splitByComplex(String writePath, String nameFormat, List<OracleBean> beanList) {
		List<Class> classes = Lists.newArrayList(OracleIfStatement.class, OracleForStatement.class, OracleLoopStatement.class, OracleFetchStatement.class, OracleExceptionStatement.class);
		Map<String, List<OracleBean>> map = MapListUtils.beanToMap(beanList, bean -> {
			for (SqlStmt stmt : bean.getSqlList()) {
				boolean isComplex = classes.stream().filter(c -> c.isInstance(stmt.getStatement())).count() > 0;
				if (isComplex) return "complex";
			}
			return "simple";
		});

		Map<String, List<String>> sqlMap = Maps.newHashMap();
		map.entrySet().stream().forEach(entry -> {
			sqlMap.putIfAbsent(entry.getKey(), new ArrayList<>());
			entry.getValue().stream().forEach(t -> {
				sqlMap.get(entry.getKey()).addAll(t.getBlock().getSqlList());
			});
		});
		sqlMap.entrySet().stream().forEach(entry -> {
				// 由jdk8升到openjdk13后，binding包不见了。
			/*try {
				SqlUtils.writeFileStr(writePath, StringFormatter.format(nameFormat, entry.getKey() + "(" + map.get(entry.getKey()).size() + ")").getValue(), entry.getValue());
			} catch (IOException e) {
				log.error(entry.getKey(), e);
			}*/
		});
	}

	/**
	 * 将OracleBean中statement中有错的或为string的读出来，输出到errorFile中，将剩下的输出到rightFile中
	 *
	 * @param writePath
	 * @param errorFileName
	 * @param rightFileName
	 * @param list
	 * @param <T>
	 * @throws IOException
	 */
	public static <T extends OracleBean> void splitByError(String writePath, String errorFileName, String rightFileName, List<T> list) throws IOException {
		List<String> errorSqls = Lists.newArrayList();
		List<String> rightSqls = Lists.newArrayList();
		for (T t : list) {
			try {
				if (t.getBlock() == null)
					continue;
				long errorNum = t.getSqlList().stream().filter(sqlStmt -> sqlStmt.getErrorMsg() != null || !(sqlStmt.getStatement() instanceof SQLStatement)).count();
				if (errorNum > 0)
					errorSqls.addAll(t.getBlock().getSqlList());
				else
					rightSqls.addAll(t.getBlock().getSqlList());
			} catch (Exception e) {
				log.error(t, e);
			}
		}

		if (!errorSqls.isEmpty())
			SqlUtils.writeFileStr(writePath, errorFileName, errorSqls);
		if (!rightSqls.isEmpty())
			SqlUtils.writeFileStr(writePath, rightFileName, rightSqls);
	}

	/**
	 * 按OracleBean中Oracle语句的数目进行划分
	 *
	 * @param writePath
	 * @param nameFormat
	 * @param maxNum     语句数目超过此数（大于）的Bean会合并输出到more文件中,传入小于0的数目则不会合并。
	 * @param list
	 * @param <T>
	 */
	public static <T extends OracleBean> void splitByStmtNum(String writePath, String nameFormat, int maxNum, List<T> list) {
//		Map<Object, List<T>> beanMap = MapListUtils.beanToMap(list, t-> maxNum < 0 || maxNum >= t.getSqlList().size() ? t.getSqlList().size() : "more");
//		System.out.println(MapListUtils.toSizeOutput(beanMap));
		Map<Object, List<String>> sqlMap = Maps.newHashMap();
		list.stream().forEach(t -> {
			Object key;
			int size = t.getSqlList().size();
			if (maxNum < 0 || maxNum >= size)
				key = size;
			else
				key = "more";
			sqlMap.putIfAbsent(key, new ArrayList<>());
			sqlMap.get(key).addAll(t.getBlock().getSqlList());
		});
		sqlMap.entrySet().stream().forEach(entry -> {
			// 由jdk8升到openjdk13后，binding包不见了。
			/*try {
				SqlUtils.writeFileStr(writePath, StringFormatter.format(nameFormat, entry.getKey()).getValue(), entry.getValue());
			} catch (IOException e) {
				log.error(entry.getKey(), e);
			}*/
		});
	}

	/**
	 * 按第一条sql的类型进行分割。
	 * 针对大量只有一条逻辑的存储过程而写
	 *
	 * @param writePath
	 * @param nameFormat
	 * @param list
	 * @param <T>
	 */
	public static <T extends OracleBean> void splitByFirstSqlType(String writePath, String nameFormat, List<T> list) {
		Map<Object, List<String>> sqlMap = Maps.newHashMap();
		Map<Object, List<T>> beanMap = MapListUtils.beanToMap(list, t -> t.getSqlList().get(0).getStatement().getClass().getSimpleName());
		list.stream().forEach(t -> {
			Object key = t.getSqlList().get(0).getStatement().getClass().getSimpleName();
			sqlMap.putIfAbsent(key, new ArrayList<>());
			sqlMap.get(key).addAll(t.getBlock().getSqlList());
		});
		sqlMap.entrySet().stream().forEach(entry -> {
			// 由jdk8升到openjdk13后，binding包不见了。
			/*try {
				SqlUtils.writeFileStr(writePath, StringFormatter.format(nameFormat, entry.getKey() + "(" + beanMap.get(entry.getKey()).size() + ")").getValue(), entry.getValue());
			} catch (IOException e) {
				log.error(entry.getKey(), e);
			}*/
		});
	}

	public static void splitByBlockNum(List<SqlBlock> blocks, int num, String outDir, String name) throws IOException {
		if (num <= 0) return;
		if (blocks == null || blocks.isEmpty()) return;
		int start = 0;
		int end = num;
		int i=0;
		while (true) {
			i++;
			if (blocks.size() >= end) {
				SqlUtils.writeFile(outDir, name + i + ".sql", blocks.subList(start, end));
			} else {
				SqlUtils.writeFile(outDir, name + i + ".sql", blocks.subList(start, blocks.size()));
				break;
			}
			start = end;
			end += num;
		}
	}
}
