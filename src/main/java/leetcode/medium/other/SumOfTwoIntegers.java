package leetcode.medium.other;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/29.
 * 371. 两整数之和
 * https://leetcode-cn.com/problems/sum-of-two-integers/solution/
 */
public class SumOfTwoIntegers {

	public int getSum(int a, int b) {
		while (b != 0) {
			int tmp = a ^ b;
			b = (a & b) << 1;
			a = tmp;
		}
		return a;
	}

	public static void main(String[] args){
		SumOfTwoIntegers main = new SumOfTwoIntegers();
		ResultCheck.check(main.getSum(1,2), 3);
		ResultCheck.check(main.getSum(-2,3), 1);
		ResultCheck.check(main.getSum(-2147483648,1), -2147483647);
	}
}
