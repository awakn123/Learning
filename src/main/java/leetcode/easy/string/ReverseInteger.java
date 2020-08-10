package leetcode.easy.string;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/6.
 * 7. Reverse Integer
 * https://leetcode.com/problems/reverse-integer/
 */
public class ReverseInteger {
	public int reverse(int x) {
		return 0;
	}
	public static void main(String[] args){
		ReverseInteger main = new ReverseInteger();
		int xI = 123;
		int answerI = 321;
		int resultI = main.reverse(xI);
		ResultCheck.check(resultI, answerI);
		int xII = -123;
		int answerII = -321;
		int resultII = main.reverse(xII);
		ResultCheck.check(resultII, answerII);
		int xIII = Integer.MIN_VALUE;
		int answerIII = 0;
		int resultIII = main.reverse(xIII);
		ResultCheck.check(resultIII, answerIII);
	}
}
