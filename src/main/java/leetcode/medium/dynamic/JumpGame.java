package leetcode.medium.dynamic;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/26.
 * 55. 跳跃游戏
 * https://leetcode-cn.com/problems/jump-game/solution/
 */
public class JumpGame {
	public boolean canJump(int[] nums) {
		return false;
	}
	public static void main(String[] args){
		JumpGame main = new JumpGame();
		ResultCheck.check(main.canJump(new int[]{2,3,1,1,4}), true);
		ResultCheck.check(main.canJump(new int[]{3,2,1,0,4}), false);
	}
}
