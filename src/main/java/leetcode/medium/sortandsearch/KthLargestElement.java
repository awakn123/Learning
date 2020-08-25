package leetcode.medium.sortandsearch;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/25.
 * 215. 数组中的第K个最大元素
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/
 */
public class KthLargestElement {

	public int findKthLargest(int[] nums, int k) {
		return 0;
	}

	public static void main(String[] args){
		KthLargestElement main = new KthLargestElement();
		ResultCheck.check(main.findKthLargest(new int[]{3,2,1,5,6,4}, 2), 5);
		ResultCheck.check(main.findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4), 4);
	}
}
