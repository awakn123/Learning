package leetcode.medium.sortandsearch;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/25.
 * 347. 前 K 个高频元素
 * https://leetcode-cn.com/problems/top-k-frequent-elements/solution/
 */
public class TopKFrequentElements {

	public int[] topKFrequent(int[] nums, int k) {
		return nums;
	}

	public static void main(String[] args){
		TopKFrequentElements main = new TopKFrequentElements();
		ResultCheck.check(main.topKFrequent(new int[]{1,1,1,2,2,3}, 2), new int[]{1,2});
		ResultCheck.check(main.topKFrequent(new int[]{1}, 1), new int[]{1});
	}
}
