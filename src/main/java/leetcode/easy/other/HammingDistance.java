package leetcode.easy.other;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/17.
 * 461. Hamming Distance
 * https://leetcode.com/problems/hamming-distance/
 */
public class HammingDistance {

	public int hammingDistance(int x, int y) {
//		return Integer.bitCount(x ^ y);
		int z = x ^ y, result = 0;
		while(z != 0) {
			result++;
			z &= z-1;
		}
		return result;
	}

	public static void main(String[] args){
		HammingDistance main = new HammingDistance();
		ResultCheck.check(main.hammingDistance(1, 4), 2);
	}
}
