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
		dataSource.setUrl("jdbc:mysql://192.168.7.44:3306/trans_record?characterEncoding=utf8");
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
    public static DruidDataSource getSqlServerDataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=e9");
        dataSource.setUsername("sa");
        dataSource.setPassword("123456");
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
	    String s = ",,,";
        String a = s.substring(0, 0);
        System.out.println(s.substring(0,0));
        /*try (DruidDataSource dataSource = getMysqlDataSource()) {
			DruidPooledConnection conn = dataSource.getConnection();
//			ResultSet rs = testCall(conn);
			PreparedStatement ps = conn.prepareStatement("update Text_Test set text_col = ?");
			ps.setString(1, null);
			ps.execute();
			*//*ResultSet rs = ps.getResultSet();
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
			System.out.println(count);*//*
			System.out.println("----------------------------value end-----------------------------");
		}*/
	}

	private static ResultSet testCall(DruidPooledConnection conn) throws SQLException {
		CallableStatement callstmt = conn.prepareCall("call HTMLLABELINDEX_SELECT_BYDESC(?,?,?)");
		callstmt.setString(1, "主目录");
		callstmt.registerOutParameter(2, Types.INTEGER);
		callstmt.registerOutParameter(3, Types.VARCHAR);
		boolean result = callstmt.execute();
		System.out.println(result);
		return callstmt.getResultSet();
	}
}
