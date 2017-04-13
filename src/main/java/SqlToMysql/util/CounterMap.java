package SqlToMysql.util;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterMap<K> extends HashMap<K, AtomicInteger> {

	public int increment(K key) {
		AtomicInteger i = this.get(key);
		if (i == null)
			return -1;
		return i.incrementAndGet();
	}

	public int getValue(K key) {
		AtomicInteger i = this.get(key);
		if (i == null)
			return -1;
		return i.get();
	}
	public int incrementAndGet(K key) {
		return increment(key);
	}

	public void put(K key) {
		this.put(key, new AtomicInteger(0));
	}

	public void putIfAbsent(K key) {
		this.putIfAbsent(key, new AtomicInteger(0));
	}

	public int putOrIncrement(K key) {
		AtomicInteger i = this.get(key);
		if (i == null) {
			i = new AtomicInteger(0);
			this.put(key, i);
		}
		return i.incrementAndGet();
	}

	public void outputToConsole() {
		this.entrySet().stream().forEach(e -> System.out.printf("%s:%s\n", e.getKey(), e.getValue().get()));
	}
}
