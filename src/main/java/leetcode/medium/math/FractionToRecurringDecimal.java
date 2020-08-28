package leetcode.medium.math;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/28.
 * 166. 分数到小数
 * https://leetcode-cn.com/problems/fraction-to-recurring-decimal/solution/
 */
public class FractionToRecurringDecimal {
	public String fractionToDecimal(int numerator, int denominator) {
		return "";
	}

	public static void main(String[] args){
		FractionToRecurringDecimal main = new FractionToRecurringDecimal();
		ResultCheck.check(main.fractionToDecimal(1, 2), "0.5");
		ResultCheck.check(main.fractionToDecimal(2, 1), "2");
		ResultCheck.check(main.fractionToDecimal(2, 3), "0.(6)");
	}
}
