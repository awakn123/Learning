package SqlToMysql.inter;

import SqlToMysql.bean.SqlBlock;

public interface BeanCreate<T> {
	T createBean(SqlBlock block);
}
