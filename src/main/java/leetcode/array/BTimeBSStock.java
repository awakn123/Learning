package leetcode.array;

/**
 * Created by 曹云 on 2020/8/1.
 * 121.Best Time to Buy and Sell Stock
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
public class BTimeBSStock {
	public int maxProfit(int[] prices) {
		if (prices.length == 0 || prices.length == 1) return 0;
		int minIdx = 0;
		int profit = 0;
		for (int i=0; i<prices.length; i++){
			if (prices[i] < prices[minIdx]) {
				minIdx = i;
			} else if (prices[i] > prices[minIdx]) {
				int p = prices[i] - prices[minIdx];
				if (profit < p){
					profit = p;
				}
			}
		}
		return profit;
	}

	public static void main(String[] args){
		int[] priceI = {2,0,4,5,9,3,7};
		int profitI = 9;
		int[] priceII = {277,44,19,333,88,1110,377,5,8};
		int profitII = 1091;
		int[] priceIII = {7,6,4,3,1};
		int profitIII = 0;

		BTimeBSStock main = new BTimeBSStock();
		int rI = main.maxProfit(priceI);
		System.out.println("I right:" + (rI == profitI));
		System.out.println("result:" + rI);
		int rII = main.maxProfit(priceII);
		System.out.println("II right:" + (rII == profitII));
		System.out.println("result:" + rII);
		int rIII = main.maxProfit(priceIII);
		System.out.println("III right:" + (rIII == profitIII));
		System.out.println("result:" + rIII);
	}
}
