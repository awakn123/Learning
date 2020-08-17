package leetcode.easy.math;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/16.
 * 326. Power of Three
 * https://leetcode.com/problems/power-of-three/
 */
public class PowerThree {

	public boolean isPowerOfThree(int n) {
		while (n >= 3) {
			if (n%3 != 0)
				return false;
			n /= 3;
		}
		return n == 1;
	}

	public static void main(String[] args){
		PowerThree main = new PowerThree();
		ResultCheck.check(main.isPowerOfThree(27), true);
		ResultCheck.check(main.isPowerOfThree(0), false);
		ResultCheck.check(main.isPowerOfThree(9), true);
		ResultCheck.check(main.isPowerOfThree(45), false);
	}
}
