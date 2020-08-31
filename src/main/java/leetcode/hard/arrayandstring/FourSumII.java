package leetcode.hard.arrayandstring;

import leetcode.util.ResultCheck;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 曹云 on 2020/8/30.
 * 454. 四数相加 II
 * https://leetcode-cn.com/problems/4sum-ii/solution/
 */
public class FourSumII {
	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		int count = 0;
		Map<Integer, Integer> sumNum = new HashMap<>();
		for (int a: A)
			for (int b: B)
				sumNum.put(a+b, sumNum.getOrDefault(a+b, 0) + 1);
		for (int c: C)
			for (int d: D)
				count += sumNum.getOrDefault(-c-d, 0);
		return count;
	}
	public static void main(String[] args){
		FourSumII main = new FourSumII();
		ResultCheck.check(main.fourSumCount(new int[]{1,2}, new int[]{-2,-1}, new int[]{-1,2}, new int[]{0,2}), 2);
	}
}
