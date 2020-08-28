package leetcode.medium.design;

import java.util.*;

/**
 * Created by 曹云 on 2020/8/27.
 * 380. 常数时间插入、删除和获取随机元素
 * https://leetcode-cn.com/problems/insert-delete-getrandom-o1/solution/
 */
public class InsertDeleteGetRandomO1 {
	Map<Integer, Integer> map;
	List<Integer> list;
	Random random;

	/** Initialize your data structure here. */
	public InsertDeleteGetRandomO1() {
		map = new HashMap<>();
		list = new ArrayList<>();
		random = new Random();
	}

	/** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
	public boolean insert(int val) {
		if (map.containsKey(val))
			return false;
		list.add(val);
		map.put(val, list.size() - 1);
		return true;
	}

	/** Removes a value from the set. Returns true if the set contained the specified element. */
	public boolean remove(int val) {
		if (!map.containsKey(val))
			return false;
		Integer idx = map.get(val), lastNum = list.get(list.size() - 1);
		list.set(idx, lastNum);
		map.put(lastNum, idx);
		list.remove(list.size() - 1);
		map.remove(val);
		return true;
	}

	/** Get a random element from the set. */
	public int getRandom() {
		int idx = random.nextInt(list.size());
		return list.get(idx);
	}

	public static void main(String[] args){
		InsertDeleteGetRandomO1 main = new InsertDeleteGetRandomO1();
		System.out.printf("insert 1: %b\n", main.insert(1));
		System.out.printf("remove 2: %b\n", main.remove(2));
		System.out.printf("insert 2: %b\n", main.insert(2));
		System.out.printf("random  : %d\n", main.getRandom());
		System.out.printf("remove 1: %b\n", main.remove(1));
		System.out.printf("insert 2: %b\n", main.insert(2));
		System.out.printf("random  : %d\n", main.getRandom());
	}
}
