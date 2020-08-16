package leetcode.easy.dynamic;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/16.
 * 53. Maximum Subarray
 * https://leetcode.com/problems/maximum-subarray/
 */
public class MaximumSubarray {
	public int maxSubArray(int[] nums) {
		return 0;
	}

	public static void main(String[] args){
		MaximumSubarray main = new MaximumSubarray();
		ResultCheck.check(main.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}), 6);
	}
}
