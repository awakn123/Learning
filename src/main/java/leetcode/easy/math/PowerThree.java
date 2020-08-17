package leetcode.easy.math;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/16.
 * 326. Power of Three
 * https://leetcode.com/problems/power-of-three/
 */
public class PowerThree {

	public boolean isPowerOfThree(int n) {
		return false;
	}

	public static void main(String[] args){
		PowerThree main = new PowerThree();
		ResultCheck.check(main.isPowerOfThree(27), true);
		ResultCheck.check(main.isPowerOfThree(0), false);
		ResultCheck.check(main.isPowerOfThree(9), true);
		ResultCheck.check(main.isPowerOfThree(45), false);
	}
}
