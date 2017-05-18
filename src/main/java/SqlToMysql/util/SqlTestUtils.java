package SqlToMysql.util;

import SqlToMysql.bean.OracleBean;
import SqlToMysql.bean.OracleParam;
import SqlToMysql.bean.OracleProcedure;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.google.common.collect.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;

import static Druid.DruidMain.getMysqlDataSource;

public class SqlTestUtils {
	private static final Logger log = LogManager.getLogger();

	public static void testProcedure(List<OracleBean> beanList, DruidDataSource dataSource) throws IOException, SQLException {
		int i = 0;
		List<String> errorNames = Lists.newArrayList();
		DruidPooledConnection conn = dataSource.getConnection();
		for (OracleBean b : beanList) {
			i++;
			try {
				execProcedure(b, conn);
				if (i % 100 == 0)
					System.out.println(i);
			} catch (Exception e) {
				log.error(b.getName(), e);
				errorNames.add(b.getName());
			}
		}
		System.out.println(i);
		System.out.println("end");
		System.out.println("-------------------------------------------------------------------");
		errorNames.stream().forEach(s -> log.error(s));
	}

	private static boolean execProcedure(DruidDataSource dataSource, OracleBean b) throws SQLException {
		DruidPooledConnection conn = dataSource.getConnection();
		return execProcedure(b, conn);
	}

	private static boolean execProcedure(OracleBean b, DruidPooledConnection conn) throws SQLException {
		StringBuilder sb = new StringBuilder("call ");
		sb.append(b.getName());
		sb.append("(");
		if (b.getParams() != null && !b.getParams().isEmpty()) {
			for (OracleParam op : b.getParams()) {
				if (op.getType().equals(DataTypeConvert.ORACLE_TRANSFER_CURSOR)) {
					continue;
				}
				sb.append("?");
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append(")");
		CallableStatement callstmt = conn.prepareCall(sb.toString());
		if (b.getParams() != null && !b.getParams().isEmpty()) {
			int j = 0;
			for (OracleParam op : b.getParams()) {
				if (op.getType().equals(DataTypeConvert.ORACLE_TRANSFER_CURSOR)) {
					continue;
				}
				j++;
				if (op.getInout() == null || op.getInout() == OracleParam.InOut.IN) {
					callstmt.setInt(j, 1);
				} else if (op.getInout() == OracleParam.InOut.OUT) {
					callstmt.setCharacterStream(j, new StringReader(""));
				} else {
					callstmt.setCharacterStream(j, new StringReader("1"));
				}
			}
		}
		boolean result = callstmt.execute();
		return result;
	}

	public static void main(String[] args) {
		try {
			//由系统标准输入设备构造BufferedReader对象
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			String readline;
			readline = sin.readLine(); //从系统标准输入读入一字符串
			try (DruidDataSource dataSource = getMysqlDataSource()) {
				//若从标准输入读入的字符串为 "bye"则停止循环
				List<OracleParam> opList = OracleParam.createOracleParam("userid_1 integer, rightstr_2 varchar2, level_3 integer, flag out integer, msg out varchar2", OracleParam.TRANSFER_PARAM);
				OracleProcedure op = new OracleProcedure("HRMEDITRIGHTPATH_SEBYURID", opList);
				int i=0;
				while (!readline.equals("bye")) {
					i++;
					execProcedure(dataSource, op);
					System.out.println("success:"+i);
					readline = sin.readLine(); //从系统标准输入读入一字符串
				} //继续循环
			}
		} catch (Exception e) {
			log.error(e);
		}
	}
}
