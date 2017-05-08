package Druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DruidMain {
	public static void main(String[] args) throws SQLException {
	    try (DruidDataSource dataSource = new DruidDataSource()){
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
			DruidPooledConnection conn = dataSource.getConnection();
			CallableStatement callstmt = conn.prepareCall("call ALBUMPHOTOS_SELECTALL");
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
		}
	}
}
