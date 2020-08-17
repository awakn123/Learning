package leetcode.easy.other;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/17.
 * 190. Reverse Bits
 * https://leetcode.com/problems/reverse-bits/
 */
public class ReverseBits {
	public int reverseBits(int n) {
		return 0;
	}

	public static void main(String[] args){
		ReverseBits main = new ReverseBits();
		ResultCheck.check(main.reverseBits(43261596), 964176192);
		ResultCheck.check(main.reverseBits(-3), -1073741825);
	}
}
