package leetcode.easy.math;

import leetcode.util.ResultCheck;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 曹云 on 2020/8/16.
 * 13. Roman to Integer
 * https://leetcode.com/problems/roman-to-integer/
 */
public class RomanToInteger {

	public int romanToInt(String s) {
		int result = 0;
		for (int i = s.length() - 1; i>=0; i--) {
			Character c = s.charAt(i), prevc = i > 0 ? s.charAt(i-1) : null;
			result += getValue(c);
			if (prevc != null) {
				if (prevc == 'I' && (c == 'V' || c == 'X')) {
					result--;
					i--;
				} else if (prevc == 'X' && (c == 'L' || c == 'C')) {
					result -= 10;
					i--;
				} else if (prevc == 'C' && (c == 'D' || c =='M')) {
					result -= 100;
					i--;
				}
			}
		}
		return result;
	}


	private int getValue(char ch) {
		switch(ch) {
			case 'I': return 1;
			case 'V': return 5;
			case 'X': return 10;
			case 'L': return 50;
			case 'C': return 100;
			case 'D': return 500;
			case 'M': return 1000;
			default: return 0;
		}
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
