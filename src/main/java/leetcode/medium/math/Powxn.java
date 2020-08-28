package leetcode.medium.math;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/28.
 * 50. Pow(x, n)
 * https://leetcode-cn.com/problems/powx-n/solution/
 */
public class Powxn {
	public double myPow(double x, int n) {
		return 0.0;
	}

	public static void main(String[] args){
		Powxn main = new Powxn();
		ResultCheck.check(main.myPow(2, 10), 1024);
		ResultCheck.check(main.myPow(2.1, 3), 9.261);
		ResultCheck.check(main.myPow(2, -2), 0.25);
	}
}
