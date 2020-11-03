package leetcode.hard.design;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by 曹云 on 2020/11/3.
 * 295. 数据流的中位数
 * https://leetcode-cn.com/problems/find-median-from-data-stream/
 * 本题可以用1. 分别维持最大堆与最小堆 2. 红黑树 两种方案实现。
 */
public class FindMedianFromDataStream {

	PriorityQueue<Integer> minQueue;
	PriorityQueue<Integer> maxQueue;

	/** initialize your data structure here. */
	public FindMedianFromDataStream() {
		minQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		maxQueue = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
	}

	public void addNum(int num) {
		maxQueue.add(num);
		minQueue.add(maxQueue.poll());
		if (maxQueue.size() < minQueue.size()) {
			maxQueue.add(minQueue.poll());
		}
	}

	public double findMedian() {
		if (maxQueue.size() == 0)
			return 0d;
		else if (maxQueue.size() > minQueue.size())
			return maxQueue.peek();
		else
			return (double)(maxQueue.peek() + minQueue.peek())/2;
	}
}
