package leetcode.medium.math;

import leetcode.util.ResultCheck;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by 曹云 on 2020/8/28.
 * 166. 分数到小数
 * https://leetcode-cn.com/problems/fraction-to-recurring-decimal/solution/
 */
public class FractionToRecurringDecimal {
	public String fractionToDecimal(int numerator, int denominator) {
		long decimalInt = (long)numerator/denominator;
		long mod = numerator%denominator;
		StringBuilder out = new StringBuilder();
		out.append(decimalInt);
		if (mod == 0)
			return out.toString();

		out.append('.');
		int signal = 1;
		if ((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)) {
			signal = -1;
		}
		if (mod > 0) mod = -mod;
		if (denominator > 0) denominator = -denominator;
		Map<Long, Integer> modMap = new HashMap<>();
		while(!modMap.containsKey(mod) && mod != 0) {
			long num = mod * 10;
			out.append(num/denominator);
			modMap.put(mod, out.length());
			mod = num%denominator;
		}
		if (mod != 0) {
			int recurStart = modMap.get(mod) - 1;
			out.insert(recurStart, '(');
			out.append(')');
		}
		if (signal < 0 && out.charAt(0) != '-') {
			out.insert(0, '-');
		}
		return out.toString();
	}

	public static void main(String[] args){
		FractionToRecurringDecimal main = new FractionToRecurringDecimal();
		ResultCheck.check(main.fractionToDecimal(1, 2), "0.5");
		ResultCheck.check(main.fractionToDecimal(2, 1), "2");
		ResultCheck.check(main.fractionToDecimal(2, 3), "0.(6)");
		ResultCheck.check(main.fractionToDecimal(4, 333), "0.(012)");
		ResultCheck.check(main.fractionToDecimal(-50, 8), "-6.25");
		ResultCheck.check(main.fractionToDecimal(7, -12), "-0.58(3)");
		ResultCheck.check(main.fractionToDecimal(-1, -2147483648), "0.0000000004656612873077392578125");
		ResultCheck.check(main.fractionToDecimal(-2147483648, -1), "2147483648");
	}
}
