package leetcode.medium.sortandsearch;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/25.
 * 33. 搜索旋转排序数组
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/
 */
public class SearchInRotatedSortedArray {

	public int search(int[] nums, int target) {
		return 0;
	}

	public static void main(String[] args){
		SearchInRotatedSortedArray main = new SearchInRotatedSortedArray();
		ResultCheck.check(main.search(new int[]{4,5,6,7,0,1,2}, 0), 4);
		ResultCheck.check(main.search(new int[]{4,5,6,7,0,1,2}, 3), -1);
	}
}
