package leetcode.hard.arrayandstring;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/30.
 * 227. 基本计算器 II
 * https://leetcode-cn.com/problems/basic-calculator-ii/solution/
 */
public class BasicCalculatorII {
	public int calculate(String s) {
		return 0;
	}

	public static void main(String[] args){
		BasicCalculatorII main = new BasicCalculatorII();
		ResultCheck.check(main.calculate("3+2*2"), 7);
		ResultCheck.check(main.calculate("3/2"), 1);
		ResultCheck.check(main.calculate("3+5 / 2"), 5);
	}
}
