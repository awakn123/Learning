package leetcode.hard.dynamic;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/9/16.
 * 309. 最佳买卖股票时机含冷冻期
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/
 */
public class BestTimeStockWCooldown {
	public int maxProfit(int[] prices) {
		return 0;
	}

	public static void main(String[] args){
		BestTimeStockWCooldown main = new BestTimeStockWCooldown();
		ResultCheck.check(main.maxProfit(new int[]{1,2,3,0,2}), 3);
	}
}
