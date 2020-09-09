package leetcode.hard.arrayandstring;

import leetcode.util.ResultCheck;

import java.util.Arrays;

/**
 * Created by 曹云 on 2020/8/30.
 * 287. 寻找重复数
 * https://leetcode-cn.com/problems/find-the-duplicate-number/solution/
 */
public class FindDuplicateNumber {
	public int findDuplicate1(int[] nums) {
		int slow = 0, fast = 0;
		do {
			slow = nums[slow];
			fast = nums[nums[fast]];
		} while (slow != fast);
		slow = 0;
		while (slow != fast) {
			slow = nums[slow];
			fast = nums[fast];
		}
		return slow;
	}
	public int findDuplicate(int[] nums) {
		int n = nums.length;
		int l = 1, r = n - 1, ans = -1;
		while (l <= r) {
			int mid = (l + r) >> 1;
			int cnt = 0;
			for (int i = 0; i < n; ++i) {
				if (nums[i] <= mid) {
					cnt++;
				}
			}
			if (cnt <= mid) {
				l = mid + 1;
			} else {
				r = mid - 1;
				ans = mid;
			}
		}
		return ans;

	}

	public static void main(String[] args){
		FindDuplicateNumber main = new FindDuplicateNumber();
		ResultCheck.check(main.findDuplicate(new int[]{5,4,4,3,2,1}), 4);
		ResultCheck.check(main.findDuplicate(new int[]{1,3,4,2,2}), 2);
		ResultCheck.check(main.findDuplicate(new int[]{3,1,3,4,2}), 3);
		ResultCheck.check(main.findDuplicate(new int[]{2,5,9,6,9,3,8,9,7,1}), 9);
		ResultCheck.check(main.findDuplicate(new int[]{2,1,2}), 2);
	}
}
