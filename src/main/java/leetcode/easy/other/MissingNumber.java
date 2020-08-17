package leetcode.easy.other;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/17.
 * 268. Missing Number
 * https://leetcode.com/problems/missing-number/
 */
public class MissingNumber {

	public int missingNumber2(int[] nums) {
		int result = nums.length;
		for (int i=0; i<nums.length; i++) {
			result += i;
			result -= nums[i];
		}
		return result;
	}
	public int missingNumber(int[] nums) {
		int result = nums.length;
		for (int i=0; i<nums.length; i++) {
			result ^= i;
			result ^= nums[i];
		}
		return result;
	}

	public static void main(String[] args){
		MissingNumber main = new MissingNumber();
		ResultCheck.check(main.missingNumber(new int[]{3,0,1}), 2);
		ResultCheck.check(main.missingNumber(new int[]{9,6,4,2,3,5,7,0,1}), 8);
	}
}
