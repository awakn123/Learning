package leetcode.medium.sortandsearch;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/25.
 * 75.颜色分类
 * https://leetcode-cn.com/problems/sort-colors/solution/
 */
public class SortColors {
	public void sortColors(int[] nums) {

	}

	public static void main(String[] args){
		SortColors main = new SortColors();
		int[] nums = new int[]{2,0,2,1,1,0};
		main.sortColors(nums);
		ResultCheck.check(nums, new int[]{0,0,1,1,2,2});
	}
}
