package leetcode.easy.math;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/16.
 * 13. Roman to Integer
 * https://leetcode.com/problems/roman-to-integer/
 */
public class RomanToInteger {

	public int romanToInt(String s) {
		return 0;
	}

	public static void main(String[] args){
		RomanToInteger main = new RomanToInteger();
		ResultCheck.check(main.romanToInt("III"), 3);
		ResultCheck.check(main.romanToInt("IV"), 4);
		ResultCheck.check(main.romanToInt("IX"), 9);
		ResultCheck.check(main.romanToInt("LVIII"), 58);
		ResultCheck.check(main.romanToInt("MCMXCIV"), 1994);
	}
}
