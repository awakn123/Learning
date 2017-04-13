package SqlToMysql.util;

import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class BeanUtils {

	public static <K,V> Map<K, List<V>> beanToMap(List<V> beanList,Function<V, K> f) {
		if (beanList == null)
			return null;
		Map<K, List<V>> m = Maps.newHashMap();
		beanList.stream().forEach(bean -> {
			K key = f.apply(bean);
			m.putIfAbsent(key, new ArrayList<V>());
			m.get(key).add(bean);
		});
		return m;
	}

}
