package leetcode.hard.dynamic;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/9/16.
 * 309. 最佳买卖股票时机含冷冻期
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/
 */
public class BestTimeStockWCooldown {
	public int maxProfit(int[] prices) {
		int n = prices.length;
		if (n == 0)
			return 0;
		int[] buy = new int[n];// max profit with one stock of each day.
		int[] profit = new int[n];// max profit of each day.
		buy[0] = -prices[0];
		profit[0] = 0;
		for (int i = 1; i < n; i++) {
			int price = prices[i];
			buy[i] = i < 2 ? Math.max(buy[i-1], -price) : Math.max(buy[i-1], profit[i-2] - price);
			profit[i] = Math.max(profit[i-1], buy[i] + price);
		}
		return profit[n - 1];
	}

	public static void main(String[] args){
		BestTimeStockWCooldown main = new BestTimeStockWCooldown();
		ResultCheck.check(main.maxProfit(new int[]{1,2,3,0,2}), 3);
		ResultCheck.check(main.maxProfit(new int[]{1,2,3,4,5}), 4);
		ResultCheck.check(main.maxProfit(new int[]{5,4,3,2,1}), 0);
		ResultCheck.check(main.maxProfit(new int[]{1,5,0,1,6}), 9);
		ResultCheck.check(main.maxProfit(new int[]{1,5,0,6,6}), 6);
		ResultCheck.check(main.maxProfit(new int[]{6,1,3,2,4,7}), 6);

	}
}
