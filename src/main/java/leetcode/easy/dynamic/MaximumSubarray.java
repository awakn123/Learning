package leetcode.easy.dynamic;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/16.
 * 53. Maximum Subarray
 * https://leetcode.com/problems/maximum-subarray/
 */
public class MaximumSubarray {
	public int maxSubArray(int[] nums) {
		if (nums.length == 0) return 0;
		int maxNum = nums[0], maxNumWithNext = nums[0];
		for (int i=1; i<nums.length; i++) {
			maxNumWithNext = Math.max(maxNumWithNext + nums[i], nums[i]);
			maxNum = Math.max(maxNum, maxNumWithNext);
		}
		return maxNum;
	}

	public static void main(String[] args){
		MaximumSubarray main = new MaximumSubarray();
		ResultCheck.check(main.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}), 6);
	}
}
