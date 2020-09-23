package leetcode.hard.dynamic;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/9/18.
 * 123. 买卖股票的最佳时机 III
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
 */
public class BestTimeStockIII {
	public int maxProfit(int[] prices) {
		int profitBuy1st = Integer.MIN_VALUE, profitSell1st = 0, profitBuy2nd = Integer.MIN_VALUE, profitSell2nd = 0;
		for (int price:prices) {
			profitBuy1st = Math.max(profitBuy1st, - price);
			profitSell1st = Math.max(profitSell1st, profitBuy1st + price);
			profitBuy2nd = Math.max(profitBuy2nd, profitSell1st - price);
			profitSell2nd = Math.max(profitSell2nd, profitBuy2nd + price);
		}
		return profitSell2nd;
	}

	public static void main(String[] args){
		BestTimeStockIII main = new BestTimeStockIII();
		ResultCheck.check(main.maxProfit(new int[]{3,3,5,0,0,3,1,4}), 6);
		ResultCheck.check(main.maxProfit(new int[]{1,2,3,4,5}), 4);
		ResultCheck.check(main.maxProfit(new int[]{7,6,4,3,1}), 0);
		ResultCheck.check(main.maxProfit(new int[]{1}), 0);
		ResultCheck.check(main.maxProfit(new int[]{1,5,2,6}), 8);
		ResultCheck.check(main.maxProfit(new int[]{1,5,2,6,1,6}), 10);
	}
}
