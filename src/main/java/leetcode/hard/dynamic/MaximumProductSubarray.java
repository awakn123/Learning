package leetcode.hard.dynamic;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/9/16.
 * 152. 乘积最大子数组
 * https://leetcode-cn.com/problems/maximum-product-subarray/solution/
 */
public class MaximumProductSubarray {
	public int maxProduct(int[] nums) {
		return 0;
	}

	public static void main(String[] args){
		MaximumProductSubarray main = new MaximumProductSubarray();
		ResultCheck.check(main.maxProduct(new int[]{2,3,-2,4}), 6);
		ResultCheck.check(main.maxProduct(new int[]{-2,0, -3}), 0);
	}
}
