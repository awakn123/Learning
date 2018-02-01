package SqlToMysql.util;

import SqlToMysql.bean.OracleBean;
import SqlToMysql.bean.OracleParam;
import SqlToMysql.bean.SqlBlock;
import SqlToMysql.bean.SqlFile;
import SqlToMysql.statement.O2MVisitor;
import SqlToMysql.statement.SqlStmt;
import SqlToMysql.type.SqlType;
import SqlToMysql.type.TypeService;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SqlUtils {
	private static final Logger log = LogManager.getLogger();
	/**
	 * 去除评论
	 * @param sqlList
	 * @return
	 */
	public static List<String> stripComment(List<String> sqlList) {
		if (sqlList == null || sqlList.isEmpty()) return sqlList;
		List<String> noCommentSqlList = Lists.newArrayList();
		boolean isComment = false;//当前行是否为注释
		for (int i = 0; i < sqlList.size(); i++) {
			String str = sqlList.get(i);
			if (StringUtils.isBlank(str))
				continue;
			StringBuffer preBuffer = new StringBuffer();
			while (str.indexOf("/*") >= 0 || isComment) {
				if (!isComment) {
					isComment = true;
					preBuffer.append(str, 0, str.indexOf("/*"));
				}
				int commentEnd = str.indexOf("*/");
				if (commentEnd >= 0) {
					isComment = false;
					str = str.substring(commentEnd + 2);
				} else {
					break;
				}
			}
			String sql = isComment ? preBuffer.toString() : preBuffer.append(str).toString();
			//“--”判断
			if (sql.indexOf("--") >= 0) {
				sql = sql.substring(0, sql.indexOf("--"));
			}

			if (StringUtils.isBlank(sql))
				continue;
			noCommentSqlList.add(sql);
		}
		return noCommentSqlList;
	}

	/**
	 * 合并List，去除空格与制表符
	 *
	 * @param sqlList
	 * @return
	 */
	public static String mergeAndTrim(List<String> sqlList) {
		String sql = sqlList.stream().reduce((str, one) -> str = str + " " + one).orElse("");
		return mergeAndTrim(sql);
	}

	public static String mergeAndTrim(String sql) {
		String noTab = StringUtils.replaceAll(sql, "\t", " ");
		return StringUtils.replaceAll(noTab, " +", " ");
	}
	public static Map<SqlType, List<SqlBlock>> classfiedBySqlType(List<SqlBlock> blocks) {
		Map<SqlType, List<SqlBlock>> typeToBlockMap = Maps.newHashMap();
		blocks.stream().forEach(sqlBlock -> {
			typeToBlockMap.putIfAbsent(sqlBlock.getSqlType(), new ArrayList<>());
			typeToBlockMap.get(sqlBlock.getSqlType()).add(sqlBlock);
		});
		return typeToBlockMap;
	}


	/**
	 * 将sqlfile中的一段写出到文件
	 *
	 * @param writeFilePath
	 * @param fileContent
	 * @param begin
	 * @param end
	 * @throws IOException
	 */
	public static void writeFile(String writeFilePath, SqlFile fileContent, int begin, int end) throws IOException {
		writeFileStr(writeFilePath, fileContent.getContentList().subList(begin, end));
	}

	public static void writeFile(String directoryPath, String name, List<SqlBlock> blocks) throws IOException {
		List<String> sqls = Lists.newArrayList();
		blocks.stream().forEach(sqlBlock -> {
			if (sqlBlock.isAllComment()) {
				return;
			}
			sqls.addAll(sqlBlock.getSqlList());
		});
		writeFileStr(directoryPath + "/" + name, sqls);
	}

	public static void writeFileStr(String directoryPath, String name, List<String> list) throws IOException {
		writeFileStr(directoryPath + "/" + name, list);
	}

	public static void writeFileStr(String pathStr, List<String> list) throws IOException {
		if (list == null || list.isEmpty())
			return;
		Path path = Paths.get(pathStr);
		File file = path.toFile();
		if (file.exists()) {
			file.delete();
		}
		Files.write(path, list, StandardOpenOption.CREATE_NEW);
	}
	/**
	 * 读取Bean中的Statement，判断组合并输出各statement类型的数量
	 * @param list
	 */
	public static <T extends OracleBean> void outputStatementSize(List<T> list) {
		Map<String, List<SqlStmt>> map = Maps.newHashMap();
		list.stream().forEach(t -> {
			List<SqlStmt> sqlList = t.getSqlList();
			if (sqlList == null) {
				System.out.println("null:" + t);
				return;
			}
			Map<String, List<SqlStmt>> m = MapListUtils.beanToMap(sqlList, sqlStmt -> {
				if (sqlStmt == null || sqlStmt.getStatement() == null) return "null";
				SqlStmt s = (SqlStmt) sqlStmt;
				if (s.getStatement() instanceof SQLStatement) {
					SQLStatement stmt = (SQLStatement) s.getStatement();
					return stmt.getClass().getName();
				} else if (s.getStatement() == null){
					return "null";
				} else if (s.getErrorMsg() != null) {
					log.error("name:" + t + ",error:"+s.getErrorMsg());
					return "error";
				} else {
					log.error("name:" + t + ",string:"+s.getStatement());
					return "string";
				}
			});
			MapListUtils.reduce(map, m);
		});
		System.out.println(MapListUtils.toSizeOutput(map));
	}


	/**
	 * 将Bean转为Mysql语法并写出到文件
	 *
	 * @param writePath
	 * @param list
	 * @throws IOException
	 */
	public static <T extends OracleBean> void listToMysqlFile(String writePath, String name, List<T> list) throws IOException {
        List<String> sqls = listToMysql(list);

		// 写出到文件
		SqlUtils.writeFileStr(writePath, name, sqls);
	}

    /**
     * 将Bean转为Mysql语法的List
     * @param list
     * @param <T>
     * @return
     */
    public static <T extends OracleBean> List<String> listToMysql(List<T> list) {
        List<String> sqls = Lists.newArrayList();
        for (T t : list) {
            try {
                if (t.getBlock() == null || t.getBlock().getSqlType() == null || !(t.getBlock().getSqlType() instanceof TypeService))
                    continue;
                TypeService typeService = (TypeService) t.getBlock().getSqlType();
                sqls.add(typeService.toMysqlSyntax(t));
            } catch (Exception e) {
                log.error(t, e);
            }
        }
        // 输出Visitor中的统计
        O2MVisitor.counter.output();
        System.out.println(O2MVisitor.counter.size());
        System.out.println(O2MVisitor.counter.output());
        System.out.println("------------------------------------------------------------------------------");
        System.out.println(O2MVisitor.okCounter.output());
        System.out.println(MapListUtils.toOutput(O2MVisitor.errorMsgs));
        return sqls;
    }


    public static List<OracleBean> blockToOracleBean(List<SqlBlock> blocks) {
		if (blocks == null || blocks.isEmpty()) return null;
		List<OracleBean> beanList = Lists.newArrayList();
		for (SqlBlock block: blocks) {
			if (block.getSqlType() == null) {
				SqlType.getOracleType().stream().filter(sqlType -> sqlType.check(block)).forEach(sqlType -> {
					block.setSqlType(sqlType);
					block.splitToObject();
				});
			}
			if (block.getSqlType() instanceof TypeService) {
				TypeService typeService = (TypeService) block.getSqlType();
				beanList.add(typeService.createBean(block));
			} else
				beanList.add(new OracleBean(block));
		}
		return beanList;
	}
	/**
	 * 输出call,用于在空数据库做测试
	 * @param writePath
	 * @param name
	 * @param beanList
	 * @throws IOException
	 */
	public static void outputCall(String writePath, String name, List<OracleBean> beanList) throws IOException {
		List<String> callStrings = getCall(beanList);
		SqlUtils.writeFileStr(writePath, name, callStrings);
	}

	public static List<String> getCall(List<OracleBean> beanList) {
		return beanList.stream().map(b -> {
				StringBuilder sb = new StringBuilder("call ");
				sb.append(b.getName());
				sb.append("(");
				if (b.getParams() != null && !b.getParams().isEmpty()) {
					int in = 0;
					int out = 0;
					for (OracleParam op : b.getParams()) {
						if (op.getType().equals(DataTypeConvert.ORACLE_TRANSFER_CURSOR)) {
							continue;
						}
						if (op.getInout() == null || op.getInout() == OracleParam.InOut.IN) {
							in++;
							sb.append(in);
						} else if (op.getInout() == OracleParam.InOut.OUT || op.getInout() == OracleParam.InOut.INOUT) {
							out++;
							sb.append("@"+out);
						}
						sb.append(",");
					}
					sb.deleteCharAt(sb.length() - 1);
				}
				sb.append(");");
				return sb.toString();
			}).collect(Collectors.toList());
	}
}
