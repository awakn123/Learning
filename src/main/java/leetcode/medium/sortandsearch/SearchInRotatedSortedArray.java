package leetcode.medium.sortandsearch;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/25.
 * 33. 搜索旋转排序数组
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/
 */
public class SearchInRotatedSortedArray {

	public int search(int[] nums, int target) {
		return search(nums, target, 0, nums.length);
	}

	private int search1(int[] nums, int target, int left, int right) {
		if (left >= right)
			return -1;
		int mid = (right - left)/2 + left;
		if (nums[mid] == target)
			return mid;
		if (nums[left] == target)
			return left;
		if (nums[right - 1] == target)
			return right - 1;

		boolean bigM = target > nums[mid], bigL = target > nums[left], bigR = target > nums[right - 1];
		if (bigM && !bigR)
			return search1(nums, target, mid + 1, right);
		else if (!bigM && bigL)
			return search1(nums, target, left, mid);
		else {
			int leftIdx = search1(nums, target, left, mid);
			if (leftIdx == -1)
				return search1(nums, target, mid + 1, right);
			return leftIdx;
		}
	}

	private int search(int[] nums, int target, int left, int right) {
		if (left >= right)
			return -1;
		int mid = (right - left)/2 + left;
		if (nums[mid] == target)
			return mid;
		if (nums[left] < nums[mid]) {// left part is sorted
			if (target < nums[mid] && target >= nums[left])
				return search(nums, target, left, mid);
			else
				return search(nums, target, mid + 1, right);
		} else {// right part is sorted
			if (target > nums[mid] && target <= nums[right - 1])
				return search(nums, target, mid + 1, right);
			else
				return search(nums, target, left, mid);
		}
	}

	public static void main(String[] args){
		SearchInRotatedSortedArray main = new SearchInRotatedSortedArray();
		ResultCheck.check(main.search(new int[]{4,5,6,7,0,1,2}, 0), 4);
		ResultCheck.check(main.search(new int[]{4,5,6,7,0,1,2}, 3), -1);
	}
}
