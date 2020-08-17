package leetcode.easy.other;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/17.
 * 191. Number of 1 Bits
 * https://leetcode.com/problems/number-of-1-bits/
 */
public class Numberof1Bits {
	// you need to treat n as an unsigned value
	public int hammingWeight2(int n) {
		String str = Integer.toBinaryString(n);
		int result = 0;
		for (int i=0; i<str.length(); i++) {
			if (str.charAt(i) == '1')
				result++;
		}
		return result;
	}
	public int hammingWeight3(int n) {
		int result = 0, mask = 1;
		for (int i=0;i<32;i++) {
			if ((n&mask) != 0){
				result++;
			}
			mask<<=1;
		}
		return result;
	}

	public int hammingWeight(int n) {
		int result = 0;
		while (n!=0) {
			result++;
			n = n&(n-1);
		}
		return result;
	}


	public static void main(String[] args){
		Numberof1Bits main = new Numberof1Bits();
		ResultCheck.check(main.hammingWeight(Integer.parseInt("00000000000000000000000000001011", 2)), 3);
		ResultCheck.check(main.hammingWeight(Integer.parseInt("00000000000000000000000010000000", 2)), 1);
		ResultCheck.check(main.hammingWeight(-3), 31);
	}
}
