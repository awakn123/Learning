package MyBatis.util;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseIdProvider implements org.apache.ibatis.mapping.DatabaseIdProvider {
	@Override
	public void setProperties(Properties p) {

	}

	@Override
	public String getDatabaseId(DataSource dataSource) throws SQLException {
		return "mysql";
	}
}
