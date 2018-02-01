package DatabaseToBean;

import Druid.DruidMain;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Main {
    private static final Logger log = LogManager.getLogger();
    public static void main(String[] args) {
        try (DruidDataSource dataSource = DruidMain.getSqlServerDataSource()) {
            DruidPooledConnection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from HtmlModuleInfo");
            boolean result = ps.execute();
            if (!result) {
                log.error("sql执行失败");
                return;
            }
            ResultSet rs = ps.getResultSet();
            ResultSetMetaData m = rs.getMetaData();
            for (int i=1;i<=m.getColumnCount();i++) {
                System.out.println(m.getColumnName(i));
            }
            int count = 0;
            while (rs.next()) {
                count++;
            }
            System.out.println(count);
        } catch (SQLException e) {
            log.error(e);
        }
    }
}
