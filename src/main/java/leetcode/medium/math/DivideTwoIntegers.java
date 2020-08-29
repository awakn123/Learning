package leetcode.medium.math;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/28.
 * 29. 两数相除
 * https://leetcode-cn.com/problems/divide-two-integers/solution/
 */
public class DivideTwoIntegers {
	public int divide(int dividend, int divisor) {
		if (dividend == 0) return 0;
		if (divisor == 1) return dividend;
		if (divisor == -1) {
			if (dividend>Integer.MIN_VALUE)
				return -dividend;
			else
				return Integer.MAX_VALUE;
		}
		long dd = (long) dividend, ds = (long) divisor;
		if (dd < 0) dd = -dd;
		if (ds < 0) ds = -ds;
		if (dd < ds) return 0;
		long r = 0, dsOri = ds;
		while (true) {
			int cal = 1;
			ds = dsOri;
			while (dd > (ds + ds)) {
//				ds += ds;
//				cal += cal;
				ds = ds << 1;
				cal = cal << 1;
			}
			r += cal;
			if (dd >= ds && dd < ds + dsOri)
				break;
			dd -= ds;
		}
		if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0))
			return (int)-r;
		else
			return (int)r;
	}

	public static void main(String[] args){
		DivideTwoIntegers main = new DivideTwoIntegers();
		ResultCheck.check(main.divide(10, 3), 3);
		ResultCheck.check(main.divide(7, -3), -2);
		ResultCheck.check(main.divide(-2147483648, -1), 2147483647);
		ResultCheck.check(main.divide(2, 2), 1);
	}
}
