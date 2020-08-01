package leetcode.array;

/**
 * Created by 曹云 on 2020/8/1.
 * 122.Best Time to Buy and Sell Stock II
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class BTimeBSStockII {
	public int maxProfit(int[] prices) {
		if (prices.length == 0 || prices.length == 1) return 0;
		int minIdx = 0;
		int profit = 0;
		int sump = 0;
		for (int i=1; i<prices.length; i++){
			if (prices[i] < prices[i-1]) {
				minIdx = i;
				sump += profit;
				profit = 0;
			} else {
				profit = prices[i] - prices[minIdx];
			}
		}
		sump += profit;
		return sump;
	}

	public static void main(String[] args){
		int[] priceI = {2,0,4,5,9,3,7};
		int profitI = 13;
		int[] priceII = {277,44,19,333,88,1110,377,5,8};
		int profitII = 1339;
		int[] priceIII = {7,6,4,3,1};
		int profitIII = 0;

		BTimeBSStockII main = new BTimeBSStockII();
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
