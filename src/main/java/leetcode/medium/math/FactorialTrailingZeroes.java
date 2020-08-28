package leetcode.medium.math;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/28.
 * 172. 阶乘后的零
 * https://leetcode-cn.com/problems/factorial-trailing-zeroes/solution/
 */
public class FactorialTrailingZeroes {
	public int trailingZeroes(int n) {
		int num = 0;
		while (n > 4) {
			n /= 5;
			num += n;
		}
		return num;
	}
	public static void main(String[] args){
		FactorialTrailingZeroes main = new FactorialTrailingZeroes();
		ResultCheck.check(main.trailingZeroes(3), 0);
		ResultCheck.check(main.trailingZeroes(5), 1);
		ResultCheck.check(main.trailingZeroes(30), 7);
	}
}
