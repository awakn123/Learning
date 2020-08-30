package leetcode.hard.arrayandstring;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/30.
 * 238. 除自身以外数组的乘积
 * https://leetcode-cn.com/problems/product-of-array-except-self/solution/
 */
public class ProductArrayExceptSelf {
	public int[] productExceptSelf(int[] nums) {
		int[] res = new int[nums.length];
		int multiple = 1;
		for (int i=0;i<nums.length;i++) {
			res[i] = multiple;
			multiple *= nums[i];
		}
		multiple = 1;
		for (int i=nums.length-1; i>=0; i--) {
			res[i] *= multiple;
			multiple *= nums[i];
		}
		return res;
	}

	public static void main(String[] args){
		ProductArrayExceptSelf main = new ProductArrayExceptSelf();
		ResultCheck.check(main.productExceptSelf(new int[]{1,2,3,4}), new int[]{24,12,8,6});
	}
}
