package MyBatis;

import Druid.DruidMain;
import com.google.common.collect.Maps;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

public class MyBatisNoXmlMain {
	public static void main(String[] args) throws SQLException {
		DataSource dataSource = DruidMain.getMysqlDataSource();
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development", transactionFactory, dataSource);
		Configuration configuration = new Configuration(environment);
		configuration.addMapper(WorkflowBaseMapper.class);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
		Map params = Maps.newHashMap();
		params.put("isOracle", null);
		params.put("isMysql", 1);
		BoundSql bs = configuration.getMappedStatement("MyBatis.WorkflowBaseMapper.select1").getBoundSql(params);
		System.out.println(bs.getSql());
		try (SqlSession session = sqlSessionFactory.openSession()) {
//			WorkflowBaseMapper mapper = session.getMapper(WorkflowBaseMapper.class);
//			WorkflowBase wb = mapper.selectWorkflowBase(1);
//			System.out.println(wb.getWorkflowName());
//			WorkflowBase wb = (WorkflowBase) session.selectOne("MyBatis.WorkflowBaseMapper.selectWorkflowBase", 1);
//			System.out.println(wb.getWorkflowName());
//			int i = (int) session.selectOne("MyBatis.WorkflowBaseMapper.select1", params);
//			System.out.println(i);
		}
	}
}
