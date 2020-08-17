package leetcode.easy.dynamic;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/16.
 * 70. Climbing Stairs
 * https://leetcode.com/problems/climbing-stairs/
 */
public class ClimbingStairs {
	public int climbStairs2(int n) {
		int twoNum = n >> 1, result = 0;
		for (int i=0; i<twoNum+1; i++) {
			result += combination(n-i, i);
		}
		return result;
	}

	private int combination(int n, int r) {
		if (n < r) return 1;
		Long denominator, numerator, result = new Long(1);
		r = Math.min(n-r, r);
		for (int i=1; i<=r; i++) {
			numerator = new Long(n - r + i);
			denominator = new Long(i);
			result = result * numerator/ denominator;
		}
		return result.intValue();
	}


	public int climbStairs(int n) {
		int p=0, q=0, result = 1;
		for (int i=0;i<n;i++) {
			p = q;
			q = result;
			result = p+q;
		}
		return result;
	}

	public static void main(String[] args){
		ClimbingStairs main = new ClimbingStairs();
		ResultCheck.check(main.climbStairs(2), 2);
		ResultCheck.check(main.climbStairs(3), 3);
		ResultCheck.check(main.climbStairs(35), 14930352);
		ResultCheck.check(main.climbStairs(44), 1134903170);
	}
}
