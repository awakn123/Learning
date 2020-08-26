package leetcode.medium.dynamic;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/26.
 * 322. 零钱兑换
 * https://leetcode-cn.com/problems/coin-change/solution/
 */
public class CoinChange {
	public int coinChange(int[] coins, int amount) {
		return 0;
	}

	public static void main(String[] args){
		CoinChange main = new CoinChange();
		ResultCheck.check(main.coinChange(new int[]{1,2,5}, 11), 3);
		ResultCheck.check(main.coinChange(new int[]{2}, 3), -1);
	}
}
