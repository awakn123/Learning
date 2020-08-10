package leetcode.easy.string;

import leetcode.util.ResultCheck;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 曹云 on 2020/8/6.
 * 7. Reverse Integer
 * https://leetcode.com/problems/reverse-integer/
 */
public class ReverseInteger {
	/**
	 * see and reimpl.
	 * @param x
	 * @return
	 */
	public int reverse(int x) {
		int y =0;
		while(x!=0) {
			int pop = x%10;
			x /= 10;
			if (y > Integer.MAX_VALUE/10 || (y == Integer.MAX_VALUE/10 && pop > 7)) return 0;
			if (y < Integer.MIN_VALUE/10 || (y == Integer.MIN_VALUE/10 && pop < -8)) return 0;
			y = y * 10 + pop;
		}
		return y;
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
		int xIV = 1534236469;
		int answerIV = 0;
		int resultIV = main.reverse(xIV);
		ResultCheck.check(resultIV, answerIV);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
	}
}
