package leetcode.hard.dynamic;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/9/16.
 * 152. 乘积最大子数组
 * https://leetcode-cn.com/problems/maximum-product-subarray/solution/
 */
public class MaximumProductSubarray {
	public int maxProduct1(int[] nums) {
		int max = Integer.MIN_VALUE, maxWithRight = 1, minWithRight = 1;
		for (int num: nums) {
			int tmpMax = num > 0 ? maxWithRight * num : minWithRight < 0 ? minWithRight * num : num;
			int tmpMin = num > 0 ? minWithRight * num : maxWithRight > 0 ? maxWithRight * num : num;
			maxWithRight = tmpMax < num ? num : tmpMax;
			minWithRight = tmpMin > num ? num : tmpMin;
			max = Math.max(max, maxWithRight);
		}
		return max;
	}
	public int maxProduct(int[] nums) {
		int max = Integer.MIN_VALUE, maxWithRight = 1, minWithRight = 1;
		for (int num: nums) {
			int tmpMax = maxWithRight * num;
			int tmpMin = minWithRight * num;
			maxWithRight = Math.max(tmpMax, Math.max(tmpMin, num));
			minWithRight = Math.min(tmpMax, Math.min(tmpMin, num));
			max = Math.max(max, maxWithRight);
		}
		return max;
	}
	public static void main(String[] args){
		MaximumProductSubarray main = new MaximumProductSubarray();
		ResultCheck.check(main.maxProduct(new int[]{2,3,-2,4}), 6);
		ResultCheck.check(main.maxProduct(new int[]{-2,0, -3}), 0);
		ResultCheck.check(main.maxProduct(new int[]{-2,1,2, -3}), 12);
		ResultCheck.check(main.maxProduct(new int[]{-1, -2, -9, -6}), 108);
	}
}
