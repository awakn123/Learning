package leetcode.medium.sortandsearch;

import leetcode.util.ResultCheck;

import java.util.*;

/**
 * Created by 曹云 on 2020/8/25.
 * 347. 前 K 个高频元素
 * https://leetcode-cn.com/problems/top-k-frequent-elements/solution/
 */
public class TopKFrequentElements {

	public int[] topKFrequent1(int[] nums, int k) {
		Map<Integer, Integer> numMap = new HashMap<>();
		for (int i=0; i<nums.length; i++) {
			numMap.put(nums[i], numMap.getOrDefault(nums[i], 0) + 1);
		}
		Iterator<Map.Entry<Integer, Integer>> iter = numMap.entrySet().iterator();
		int[][] idx = new int[nums.length + 1][];
		while(iter.hasNext()) {
			Map.Entry<Integer, Integer> e = iter.next();
			if (idx[e.getValue()] == null) {
				idx[e.getValue()] = new int[]{e.getKey()};
			} else {
				idx[e.getValue()] = Arrays.copyOf(idx[e.getValue()], idx[e.getValue()].length + 1);
				idx[e.getValue()][idx[e.getValue()].length - 1] = e.getKey();
			}

		}

		int j = 0;
		int[] result = new int[k];
		for (int i=idx.length - 1;i>0;i--) {
			if (j == k)
				break;
			if (idx[i] != null) {
				for (int p=0;p<idx[i].length;p++) {
					if (j == k)
						break;
					result[j] = idx[i][p];
					j++;
				}
			}
		}
		return result;
	}
	public int[] topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> numMap = new HashMap<>();
		for (int i=0; i<nums.length; i++) {
			numMap.put(nums[i], numMap.getOrDefault(nums[i], 0) + 1);
		}
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return numMap.get(o1) - numMap.get(o2);
			}
		});
		for (Integer num: numMap.keySet()) {
			if (pq.size() < k) {
				pq.add(num);
			} else if (numMap.get(pq.peek()) < numMap.get(num)) {
				pq.remove();
				pq.add(num);
			}
		}
		int[] result = new int[k];
		int j = k - 1;
		while(!pq.isEmpty()) {
			result[j] = pq.poll();
			j--;
		}
		return result;
	}


	public static void main(String[] args){
		TopKFrequentElements main = new TopKFrequentElements();
		ResultCheck.check(main.topKFrequent(new int[]{1,1,1,2,2,3}, 2), new int[]{1,2});
		ResultCheck.check(main.topKFrequent(new int[]{1}, 1), new int[]{1});
	}
}
