package SqlToMysql.util;

import SqlToMysql.bean.OracleBean;
import SqlToMysql.bean.OracleParam;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.google.common.collect.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;

public class SqlTestUtils {
	private static final Logger log = LogManager.getLogger();

	public static void testProcedure(List<OracleBean> beanList, DruidDataSource dataSource) throws IOException, SQLException {
		DruidPooledConnection conn = dataSource.getConnection();
		List<String> calls = SqlUtils.getCall(beanList);
		int i = 0;
		List<String> errorNames = Lists.newArrayList();
		for (OracleBean b : beanList) {
			i++;
			try {
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
							callstmt.setInt(j, j);
						} else if (op.getInout() == OracleParam.InOut.OUT || op.getInout() == OracleParam.InOut.INOUT) {
							callstmt.setCharacterStream(j, new StringReader(""));
						}
					}
				}
				boolean result = callstmt.execute();
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
}
