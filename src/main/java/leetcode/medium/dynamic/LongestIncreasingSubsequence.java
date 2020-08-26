package leetcode.medium.dynamic;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/26.
 * 300. 最长上升子序列
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/
 */
public class LongestIncreasingSubsequence {

	public int lengthOfLIS(int[] nums) {
		return 0;
	}

	public static void main(String[] args){
		LongestIncreasingSubsequence main = new LongestIncreasingSubsequence();
		ResultCheck.check(main.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}), 4);
	}
}
