package leetcode.hard.dynamic;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/9/24.
 * 188. 买卖股票的最佳时机 IV
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
 */
public class BestTimeStockIV {
	public int maxProfit(int k, int[] prices) {
		if (k == 0 || prices.length == 0) return 0;
		if (k > prices.length/2) {
			int profit = 0;
			for (int i=1; i < prices.length; i++) {
				int tmpPro = prices[i] - prices[i-1];
				profit += tmpPro < 0 ? 0 : tmpPro;
			}
			return profit;

		}
		int[][] res = new int[k][];
		for (int i=0; i<k; i++) {
			res[i] = new int[]{Integer.MIN_VALUE, 0};
		}
		for (int price: prices) {
			for (int i=0; i<k; i++) {
				int bfprofit = i == 0 ? 0 : res[i-1][1];
				res[i][0] = Math.max(res[i][0], bfprofit - price);
				res[i][1] = Math.max(res[i][1], res[i][0] + price);
			}
		}
		return res[k-1][1];
	}

	public static void main(String[] args){
		BestTimeStockIV main = new BestTimeStockIV();
		ResultCheck.check(main.maxProfit(2, new int[]{2,4,1}), 2);
		ResultCheck.check(main.maxProfit(2, new int[]{3,2,6,5,0,3}), 7);
	}
}
