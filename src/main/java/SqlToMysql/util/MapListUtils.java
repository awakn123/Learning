package SqlToMysql.util;

import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class MapListUtils {

	/**
	 * 将list转为key-list
	 *
	 * @param beanList
	 * @param f
	 * @param <K>
	 * @param <V>
	 * @return
	 */
	public static <K, V> Map<K, List<V>> beanToMap(List<V> beanList, Function<V, K> f) {
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

	public static <K, V> Map<K, List<V>> reduce(Map<K, List<V>> sum, Map<K, List<V>> sub) {
		sub.entrySet().stream().forEach(entry -> {
			if (sum.get(entry.getKey()) != null)
				sum.get(entry.getKey()).addAll(entry.getValue());
			else
				sum.put(entry.getKey(), entry.getValue());
		});
		return sum;
	}

	/**
	 * 输出map中各key对应的list的size大小;
	 * @param map
	 * @param <K>
	 * @param <V>
	 * @return
	 */
	public static <K, V> String toSizeOutput(Map<K, List<V>> map) {
		StringBuffer sb = new StringBuffer();
		map.entrySet().stream().forEach(e -> sb.append("key:").append(e.getKey()).append(", size:").append("size:").append(e.getValue().size()).append("\n"));
		return sb.toString();
	}


	/**
	 * 输出map
	 * @param map
	 * @param <K>
	 * @param <V>
	 * @return
	 */
	public static <K, V> String toOutput(Map<K, List<V>> map) {
		StringBuffer sb = new StringBuffer();
		map.entrySet().stream().forEach(e -> {
			sb.append("key:").append(e.getKey()).append("\n");
			e.getValue().stream().forEach(v -> sb.append("\t").append(v.toString()).append("\n"));
		});
		return sb.toString();
	}
}
