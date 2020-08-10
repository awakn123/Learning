package leetcode.easy.string;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/6.
 * 8. String to Integer (atoi)
 * https://leetcode.com/problems/string-to-integer-atoi/
 */
public class StringToInteger {

	public int myAtoi(String str) {
		return 0;
	}

	public static void main(String[] args){
		StringToInteger main = new StringToInteger();
		String strI = "42";
		int answerI = 42;
		int resultI = main.myAtoi(strI);
		ResultCheck.check(resultI, answerI);
		String strII = "       -42";
		int answerII = -42;
		int resultII = main.myAtoi(strII);
		ResultCheck.check(resultII, answerII);
		String strIII = "4193 with words";
		int answerIII = 4193;
		int resultIII = main.myAtoi(strIII);
		ResultCheck.check(resultIII, answerIII);
		String strIV = "words and 987";
		int answerIV = 0;
		int resultIV = main.myAtoi(strIV);
		ResultCheck.check(resultIV, answerIV);
		String strV = "-91283472332";
		int answerV = Integer.MIN_VALUE;
		int resultV = main.myAtoi(strV);
		ResultCheck.check(resultV, answerV);

	}
}
