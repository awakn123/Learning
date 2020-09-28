package leetcode.hard.dynamic;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/9/16.
 * 279. Perfect Squares
 * https://leetcode-cn.com/problems/perfect-squares/solution/
 */
public class PerfectSquares {
	int result = 0;
	public int numSquares(int n) {
		result = n;
		backtrack(n, 0);
		return result;
	}

	private void backtrack(int n, int preNum) {
		int sqrt = (int)Math.sqrt(n);
		for (int i=sqrt; i > 0; i--) {
			int square = i * i;
			int r = n/square + preNum;
			if (result < r)
				continue;
			int nextn = n%square;
			if (nextn != 0) {
				backtrack(nextn, r);
			} else {
				result = Math.min(r, result);
			}
		}
	}

	public static void main(String[] args){
		PerfectSquares main = new PerfectSquares();
		ResultCheck.check(main.numSquares(12), 3);
		ResultCheck.check(main.numSquares(13), 2);
	}
}
