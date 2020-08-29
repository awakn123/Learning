package leetcode.medium.math;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/28.
 * 69. x 的平方根
 * https://leetcode-cn.com/problems/sqrtx/solution/
 */
public class Sqrtx {
	public int mySqrt1(int x) {
		int left = 1, right = x;
		while (left < right) {
			int mid = (right - left)/2 + left;
			long midSquare = (long) mid * mid;
			if (x < midSquare) {
				right = mid;
			} else if (x > midSquare) {
				left = mid + 1;
			} else {
				left = right = mid;
			}
		}
		if ((long)left * left > x) {
			return left - 1;
		} else {
			return left;
		}
	}

	public int mySqrt2(int x) {
		int r = (int)Math.exp(0.5 * Math.log(x));
		if ((long)(r + 1) * (r + 1) <= x) {
			r++;
		}
		return r;
	}

	public int mySqrt(int x) {
		if (x == 0) return 0;
		double C = x, x0 = x;
		while (true) {
			double xi = 0.5 * (x0 + C/x0);
			if (x0-xi<1e-7) {
				x0 = xi;
				break;
			}
			x0 = xi;
		}
		return (int)x0;
	}
	public static void main(String[] args){
		Sqrtx main = new Sqrtx();
		ResultCheck.check(main.mySqrt(4), 2);
		ResultCheck.check(main.mySqrt(8), 2);
		ResultCheck.check(main.mySqrt(2147395599), 46339);
		ResultCheck.check(main.mySqrt(2147483647), 46340);
		ResultCheck.check(main.mySqrt(2147395600), 46340);
	}
}
