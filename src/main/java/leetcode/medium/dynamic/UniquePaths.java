package leetcode.medium.dynamic;

import leetcode.util.ResultCheck;

import java.util.Arrays;

/**
 * Created by 曹云 on 2020/8/26.
 * 62. 不同路径
 * https://leetcode-cn.com/problems/unique-paths/solution/
 */
public class UniquePaths {
	public int uniquePaths1(int m, int n) {
		int min = m, max = n;
		if (m > n) {
			min = n;
			max = m;
		}
		int[] arr = null;
		for (int l = 0; l<max; l++) {
			int[] prevArr = arr;
			arr = new int[min];
			for (int i=0; i<min; i++) {
				if (i == 0 || prevArr == null)
					arr[i] = 1;
				else {
					arr[i] = arr[i-1] + prevArr[i];
				}
			}
		}
		return arr[min - 1];
	}

	public int uniquePaths(int m, int n) {
		int min = m, max = n;
		if (m > n) {
			min = n;
			max = m;
		}
		int[] arr = new int[min];
		Arrays.fill(arr, 1);
		for (int l = 1; l<max; l++) {
			for (int i=1; i<min; i++) {
				arr[i] += arr[i-1];
			}
		}
		return arr[min - 1];
	}

	public static void main(String[] args){
		UniquePaths main = new UniquePaths();
		ResultCheck.check(main.uniquePaths(3, 2), 3);
		ResultCheck.check(main.uniquePaths(7, 3), 28);
	}
}
