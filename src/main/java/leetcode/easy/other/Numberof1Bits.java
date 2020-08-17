package leetcode.easy.other;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/17.
 * 191. Number of 1 Bits
 * https://leetcode.com/problems/number-of-1-bits/
 */
public class Numberof1Bits {
	// you need to treat n as an unsigned value
	public int hammingWeight(int n) {
		return 0;
	}

	public static void main(String[] args){
		Numberof1Bits main = new Numberof1Bits();
		ResultCheck.check(main.hammingWeight(3), 3);
		ResultCheck.check(main.hammingWeight(1), 1);
		ResultCheck.check(main.hammingWeight(-3), 31);
	}
}
