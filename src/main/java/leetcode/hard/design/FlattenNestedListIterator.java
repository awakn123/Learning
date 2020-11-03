package leetcode.hard.design;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

/**
 * Created by 曹云 on 2020/11/2.
 * https://leetcode-cn.com/problems/flatten-nested-list-iterator/solution/
 */
public class FlattenNestedListIterator implements Iterator<Integer> {
	public interface NestedInteger {
		public boolean isInteger();
		public Integer getInteger();
		List<NestedInteger> getList();
	}
	public static class NestedIntImpl implements NestedInteger {
		int value;
		List<NestedInteger> list = null;
		public NestedIntImpl(List<NestedInteger> list) {
			this.list = list;
		}
		public NestedIntImpl(int value) {
			this.value = value;
		}
		@Override
		public boolean isInteger() {
			return this.list == null;
		}

		@Override
		public Integer getInteger() {
			return this.value;
		}

		@Override
		public List<NestedInteger> getList() {
			return this.list;
		}
	}
	List<NestedInteger> list;
	int pos = -1;
	FlattenNestedListIterator posIter = null;
	public FlattenNestedListIterator(List<NestedInteger> nestedList) {
		this.list = nestedList;
		this.toNext();
	}
	@Override
	public boolean hasNext() {
		return pos < list.size();
	}

	@Override
	public Integer next() {
		NestedInteger nestedInteger = this.list.get(pos);
		if(nestedInteger.isInteger()) {
			toNext();
			return nestedInteger.getInteger();
		}
		Integer v = posIter.next();
		if (!posIter.hasNext())
			toNext();
		return v;
	}

	private void toNext() {
		pos++;
		while (pos < this.list.size() && !this.list.get(pos).isInteger()) {
			posIter = new FlattenNestedListIterator(this.list.get(pos).getList());
			if (posIter.hasNext()) {
				break;
			} else {
				pos++;
			}
		}
	}

	public static List<NestedInteger> createList(int[] arr) {
		List<NestedInteger> list = Lists.newArrayList();
		for (int v : arr)
			list.add(new NestedIntImpl(v));
		return list;
	}
	public static void main(String[] args){
		List<NestedInteger> list = Lists.newArrayList(new NestedIntImpl(createList(new int[]{1,1})));
		list.add(new NestedIntImpl(2));
		list.add(new NestedIntImpl(createList(new int[]{1,1})));
		FlattenNestedListIterator main = new FlattenNestedListIterator(list);
		while (main.hasNext()) {
			System.out.println(main.next());
		}
		System.out.println("1,1,2,1,1");
		List<NestedInteger> list2 = Lists.newArrayList();
		list2.add(new NestedIntImpl(1));
		NestedInteger ni4 = new NestedIntImpl(4);
		NestedInteger ni6 = new NestedIntImpl(6);
		list2.add(new NestedIntImpl(Lists.newArrayList(ni4, new NestedIntImpl(Lists.newArrayList(ni6)))));
		FlattenNestedListIterator main2 = new FlattenNestedListIterator(list2);
		while (main2.hasNext()) {
			System.out.println(main2.next());
		}
		System.out.println("1,4,6");
		List<NestedInteger> list3 = Lists.newArrayList();
		list3.add(new NestedIntImpl(Lists.newArrayList()));
		FlattenNestedListIterator main3 = new FlattenNestedListIterator(list3);
		while (main3.hasNext()) {
			System.out.println(main3.next());
		}
	}
}
