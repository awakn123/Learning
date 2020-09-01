package leetcode.hard.arrayandstring;

import leetcode.util.ResultCheck;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by 曹云 on 2020/8/30.
 * 227. 基本计算器 II
 * https://leetcode-cn.com/problems/basic-calculator-ii/solution/
 */
public class BasicCalculatorII {
	/**
	 * 这里的小的注意点特别多。
	 * 1. Character.isDigit方法比较复杂，转为判断是否为操作符更加简单。
	 * 2. Character.digit方法也很复杂，可以转为(int)c - '0'加速。
	 * 3. 直接遍历s.toCharArray()是比s.charAt快一点的，看了下实现，应该就出在对i的范围判断上。
	 * 4. 从数组中移除、添加，在这里是比较大的开销。
	 * @param s
	 * @return
	 */
	public int calculate(String s) {
		List<Integer> numList = new ArrayList<>(s.length());
		List<Character> operList = new ArrayList<>(s.length());
		int num = 0;
		char[] chars = s.toCharArray();
		for (int i=0; i<chars.length; i++) {
			char c = chars[i];
			if (c == ' ')
				continue;
			else if (isOperation(c)) {
				if (c == '*' || c == '/') {
					int op2 = 0;
					while(++i < s.length() && !isOperation(chars[i])) {
						if (chars[i] != ' ')
							op2 = op2 * 10 + (s.charAt(i) - '0');
					}
					i--;
					if (c == '*') {
						num *= op2;
					} else{
						num /= op2;
					}
				} else {
					numList.add(num);
					num = 0;
					operList.add(c);
				}
			} else {
				num = num * 10 + (c - '0');
			}
		}
		numList.add(num);
		num = numList.get(0);
		for (int i=0; i<operList.size(); i++) {
			char c = operList.get(i);
			if (c == '+') {
				num += numList.get(i+1);
			} else {
				num -= numList.get(i+1);
			}
		}
		return num;
	}

	private boolean isOperation(char c) {
		if(c == '+' || c == '-' || c == '*' || c == '/') return true;
		return false;
	}

	public static void main(String[] args){
		BasicCalculatorII main = new BasicCalculatorII();
		ResultCheck.check(main.calculate("3+2*2"), 7);
		ResultCheck.check(main.calculate("3/2"), 1);
		ResultCheck.check(main.calculate("3+5 / 2"), 5);
		ResultCheck.check(main.calculate(" "), 0);
		ResultCheck.check(main.calculate("1"), 1);
		ResultCheck.check(main.calculate("2*3*4"), 24);
	}
}
