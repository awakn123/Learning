package Druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;

public class DruidMain {
	private static final Logger log = LogManager.getLogger();


	public static DruidDataSource getMysqlDataSource() throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl("jdbc:mysql://192.168.7.44:3306/weaver_test");
		dataSource.setUsername("root");
		dataSource.setPassword("ecology");
		dataSource.setInitialSize(1);
		dataSource.setMinIdle(1);
		dataSource.setMaxActive(20);
		dataSource.setMaxWait(60000);
		dataSource.setTimeBetweenEvictionRunsMillis(60000);
		dataSource.setMinEvictableIdleTimeMillis(300000);
		dataSource.setValidationQuery("select 'x'");
		dataSource.setTestWhileIdle(true);
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);
		dataSource.setPoolPreparedStatements(false);
		dataSource.setFilters("stat");
		dataSource.init();
		return dataSource;
	}
	public static void main(String[] args) throws SQLException, IOException {
		try (DruidDataSource dataSource = getMysqlDataSource()) {
			DruidPooledConnection conn = dataSource.getConnection();
			CallableStatement callstmt = conn.prepareCall("call ALBUMPHOTOS_SELECTALL(?,?)");
			callstmt.registerOutParameter(1, Types.INTEGER);
			callstmt.registerOutParameter(2, Types.VARCHAR);
			boolean result = callstmt.execute();
			System.out.println(result);
			ResultSet rs = callstmt.getResultSet();
			ResultSetMetaData m = rs.getMetaData();
			System.out.println("--------------------column start----------------------------");
			for (int i=1;i<=m.getColumnCount();i++) {
				System.out.println(m.getColumnName(i));
			}
			System.out.println("---------------value start---------------------");
			int count = 0;
			while (rs.next()) {
				for (int i=1;i<=m.getColumnCount();i++) {
					System.out.println(rs.getString(i));
				}
				System.out.println();
				count++;
			}
			System.out.println(count);
			System.out.println("----------------------------value end-----------------------------");
			System.out.println(callstmt.getInt(1));
			System.out.println(callstmt.getString(2));
		}
	}
}
