package leetcode.medium.math;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/28.
 * 172. 阶乘后的零
 * https://leetcode-cn.com/problems/factorial-trailing-zeroes/solution/
 */
public class FactorialTrailingZeroes {
	public int trailingZeroes(int n) {
		return 0;
	}
	public static void main(String[] args){
		FactorialTrailingZeroes main = new FactorialTrailingZeroes();
		ResultCheck.check(main.trailingZeroes(3), 0);
		ResultCheck.check(main.trailingZeroes(5), 1);
	}
}
