package leetcode.easy.dynamic;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/16.
 * 198. House Robber
 * https://leetcode.com/problems/house-robber/
 */
public class HouseRobber {
	public int rob(int[] nums) {
		if (nums.length == 0) return 0;
		int max = nums[0], lastMax = 0;
		for (int i = 1;i<nums.length; i++) {
			int tmpMax = lastMax + nums[i];
			lastMax = max;
			max = Math.max(max, tmpMax);
		}
		return max;
	}

	public static void main(String[] args){
		HouseRobber main = new HouseRobber();
		ResultCheck.check(main.rob(new int[]{1,2,3,1}), 4);
		ResultCheck.check(main.rob(new int[]{2,7,9,3,1}), 12);
	}
}
