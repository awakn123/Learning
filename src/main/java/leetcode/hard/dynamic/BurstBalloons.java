package leetcode.hard.dynamic;

import leetcode.util.ResultCheck;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 曹云 on 2020/9/16.
 * 312. Burst Balloons
 * https://leetcode-cn.com/problems/burst-balloons/solution/
 */
public class BurstBalloons {
	private int[][] calResult;
	private int[] vals;
	public int maxCoins1(int[] nums) {
		int n = nums.length;
		vals = new int[n+2];
		for (int i=0; i<n; i++) {
			vals[i+1] = nums[i];
		}
		vals[0] = 1;
		vals[n+1] = 1;
		calResult = new int[n+2][n+2];
		for (int i=0; i<calResult.length; i++) {
			Arrays.fill(calResult[i], -1);
		}
		return recurse(0, n+1);
	}

	private int recurse(int left, int right) {
		if (left >= right - 1)
			return 0;
		if (calResult[left][right] >= 0)
			return calResult[left][right];
		int max = 0;
		for (int i=left + 1; i<right; i++) {
			int leftMax = recurse(left, i);
			int rightMax = recurse(i, right);
			int sumMax = vals[left] * vals[i] * vals[right] + leftMax + rightMax;
			max = Math.max(max, sumMax);
		}
		calResult[left][right] = max;
		return max;
	}

	public int maxCoins(int[] nums) {
		int n = nums.length;
		int[] vals = new int[n+2];
		for (int i=0; i<n; i++) {
			vals[i+1] = nums[i];
		}
		vals[0] = 1;
		vals[n+1] = 1;
		int[][] calResult = new int[n+2][n+2];

		for (int len = 3; len <= n+2; len++) {
			for (int i = 0; i <= (n+2-len); i++) {
				int j = i + len - 1;
				int max = 0;
				for (int k = i+1; k < j; k++) {
					int mul = vals[i] * vals[k] * vals[j];
					int sumMax = calResult[i][k] + calResult[k][j] + mul;
					max = Math.max(max, sumMax);
				}
				calResult[i][j] = max;
			}
		}

		return calResult[0][n+1];
	}

	public static void main(String[] args){
		BurstBalloons main = new BurstBalloons();
		ResultCheck.check(main.maxCoins(new int[]{3,1,5,8}), 167);
	}
}
