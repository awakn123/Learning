package MyBatis.util;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;

public class MybatisLocalDataSourceFactory extends PooledDataSourceFactory {
	public MybatisLocalDataSourceFactory() {
		this.dataSource = new DruidDataSource();
	}

}
