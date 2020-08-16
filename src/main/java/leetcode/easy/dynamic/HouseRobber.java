package leetcode.easy.dynamic;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/16.
 * 198. House Robber
 * https://leetcode.com/problems/house-robber/
 */
public class HouseRobber {
	public int rob(int[] nums) {

		return 0;
	}

	public static void main(String[] args){
		HouseRobber main = new HouseRobber();
		ResultCheck.check(main.rob(new int[]{1,2,3,1}), 4);
		ResultCheck.check(main.rob(new int[]{2,7,9,3,1}), 12);
	}
}
