package leetcode.medium.dynamic;

import leetcode.util.ResultCheck;

import java.util.Arrays;

/**
 * Created by 曹云 on 2020/8/26.
 * 322. 零钱兑换
 * https://leetcode-cn.com/problems/coin-change/solution/
 */
public class CoinChange {

	public int coinChange1(int[] coins, int amount) {
		int[] calResult = new int[amount + 1];
		Arrays.fill(calResult, -2);
		return recurse(coins, amount, calResult);
	}
	private int recurse(int[] coins, int amount, int[] calResult) {
		if (amount < 0) return -1;
		if (amount == 0) return 0;
		if (calResult[amount] >= -1) return calResult[amount];
		int min = Integer.MAX_VALUE;
		for (int i=0;i<coins.length;i++) {
			int n = recurse(coins, amount - coins[i], calResult);
			if (n != -1 && n < min) {
				min = n + 1;
			}
		}

		calResult[amount] = min == Integer.MAX_VALUE ? -1 : min;
		return calResult[amount];
	}
	public int coinChange2(int[] coins, int amount) {
		int[] calResult = new int[amount + 1];
		Arrays.fill(calResult, -1);
		calResult[0] = 0;
		for (int i=1; i<=amount;i++) {
			int min = Integer.MAX_VALUE;
			for (int coin: coins) {
				if (i == coin)
					min = 1;
				else if (i > coin && calResult[i-coin] >= 0) {
					min = Math.min(min, calResult[i-coin] + 1);
				}
			}
			calResult[i] = min == Integer.MAX_VALUE ? -1 : min;
		}
		return calResult[amount];
	}

	private int result = Integer.MAX_VALUE;
	public int coinChange(int[] coins, int amount) {
		if (amount == 0) return 0;
		result = Integer.MAX_VALUE;
		Arrays.sort(coins);
		recurseCoin(coins, amount, coins.length - 1, 0);
		return result == Integer.MAX_VALUE ? -1 : result;
	}

	private void recurseCoin(int[] coins, int amount, int first, int curNum) {
		for (int i = first; i>=0; i--) {
			int coin = coins[i];
			int num = amount/coin;
			for (int j = num; j>0; j--) {
				int leftAmount = amount - j * coin, nextCurNum = curNum + j;
				if (leftAmount == 0) {
					result = Math.min(result, nextCurNum);
					break;
				}
				if (nextCurNum >= result)
					break;
				recurseCoin(coins, leftAmount, i - 1, nextCurNum);
			}
		}
	}


	public static void main(String[] args){
		CoinChange main = new CoinChange();
		ResultCheck.check(main.coinChange(new int[]{1,2,5}, 11), 3);
		ResultCheck.check(main.coinChange(new int[]{2}, 3), -1);
		ResultCheck.check(main.coinChange(new int[]{1}, 0), 0);
		ResultCheck.check(main.coinChange(new int[]{186,419,83,408}, 6249), 20);
	}
}
