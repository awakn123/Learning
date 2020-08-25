package leetcode.medium.sortandsearch;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/25.
 * 75.颜色分类
 * https://leetcode-cn.com/problems/sort-colors/solution/
 */
public class SortColors {
	public void sortColors(int[] nums) {
		int left = 0, right = nums.length - 1;
		for (int i=0; i<=right; i++) {
			if (nums[i] == 2) {
				swap(nums, i, right);
				right--;
				i--;
			} else if (nums[i] == 0) {
				swap(nums, i ,left);
				left++;
			}
		}
	}

	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public static void main(String[] args){
		SortColors main = new SortColors();
		int[] nums = new int[]{2,0,2,1,1,0};
		main.sortColors(nums);
		ResultCheck.check(nums, new int[]{0,0,1,1,2,2});
		int[] numsII = new int[]{2,0,1};
		main.sortColors(numsII);
		ResultCheck.check(numsII, new int[]{0,1,2});
	}
}
