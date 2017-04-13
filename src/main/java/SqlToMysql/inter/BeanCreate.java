package SqlToMysql.inter;

import SqlToMysql.bean.SqlBlock;
import com.google.common.collect.Lists;

import java.util.List;

public interface BeanCreate<T> {
	T createBean(SqlBlock block);
	default List<T> createBeanBatch(List<SqlBlock> blocks) {
		if (blocks == null)
			return null;
		List<T> list = Lists.newArrayList();
		blocks.forEach(block -> list.add(this.createBean(block)));
		return list;
	};
}
