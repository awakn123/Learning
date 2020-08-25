package leetcode.medium.sortandsearch;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/25.
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/
 */
public class FindFirstAndLastPos {

	public int[] searchRange(int[] nums, int target) {
		return nums;
	}

	public static void main(String[] args){
		FindFirstAndLastPos main = new FindFirstAndLastPos();
		ResultCheck.check(main.searchRange(new int[]{5,7,7,8,8,10}, 8), new int[]{3,4});
		ResultCheck.check(main.searchRange(new int[]{5,7,7,8,8,10}, 6), new int[]{-1,-1});
	}
}
