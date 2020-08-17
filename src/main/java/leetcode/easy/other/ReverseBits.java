package leetcode.easy.other;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/17.
 * 190. Reverse Bits
 * https://leetcode.com/problems/reverse-bits/
 */
public class ReverseBits {
	public int reverseBits2(int n) {
		int result = 0, mask = 1;
		for (int i=0; i<32; i++) {
			result <<= 1;
			if ((n&mask) != 0) {
				result++;
			}
			mask <<= 1;
		}
		return result;
	}

	/**
	 * @param n
	 * @return
	 */
	public int reverseBits(int n) {
		n = (n >>> 16) | (n << 16);
		n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
		n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
		n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
		n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
		return n;
	}

	public static void main(String[] args){
		ReverseBits main = new ReverseBits();
		ResultCheck.check(main.reverseBits(43261596), 964176192);
		ResultCheck.check(main.reverseBits(-3), -1073741825);
	}
}
