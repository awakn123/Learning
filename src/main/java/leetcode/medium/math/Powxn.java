package leetcode.medium.math;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/28.
 * 50. Pow(x, n)
 * https://leetcode-cn.com/problems/powx-n/solution/
 */
public class Powxn {
	public double myPow(double x, int n) {
		if (n == 0 || x == 1) return 1;
		long N = n;
		if (N < 0)
			N = -N;
		double r = 1;
		while (N > 1) {
			if (N%2 == 1)
				r *= x;
			x = x * x;
			N = N/2;
		}
		r *= x;
		return n > 0 ? r : 1/r;
	}

	public static void main(String[] args){
		Powxn main = new Powxn();
		ResultCheck.check(main.myPow(2, 10), 1024);
		ResultCheck.check(main.myPow(2.1, 3), 9.261);
		ResultCheck.check(main.myPow(2, -2), 0.25);
		ResultCheck.check(main.myPow(2, -2147483648), 0);
	}
}
