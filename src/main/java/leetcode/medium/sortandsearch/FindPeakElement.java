package leetcode.medium.sortandsearch;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/25.
 * 162. 寻找峰值
 * https://leetcode-cn.com/problems/find-peak-element/solution/
 */
public class FindPeakElement {

	public int findPeakElement1(int[] nums) {
		for (int i=1;i<nums.length;i++) {
			if (nums[i] < nums[i - 1])
				return i - 1;
		}
		return nums.length - 1;
	}
	public int findPeakElement(int[] nums) {
		return findPeakElement(nums, 0, nums.length - 1);
	}
	public int findPeakElement(int[] nums, int left, int right) {
		int len = right - left + 1;
		int mid = (len >> 1) + left;
		if (mid + 1 >= nums.length || nums[mid] > nums[mid + 1]) {
			if (mid - 1 < 0 || nums[mid] > nums[mid - 1])
				return mid;
			return findPeakElement(nums, left, mid - 1);
		}
		return findPeakElement(nums, mid + 1, right);
	}

	public static void main(String[] args){
		FindPeakElement main = new FindPeakElement();
		ResultCheck.check(main.findPeakElement(new int[]{1,2,3,1}), 2);
		ResultCheck.check(main.findPeakElement(new int[]{1,2,3}), 2);
		ResultCheck.check(main.findPeakElement(new int[]{1,2,1,3,5,6,4}), 1);// or 5.
	}
}
