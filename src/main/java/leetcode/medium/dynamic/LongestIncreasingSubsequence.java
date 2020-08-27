package leetcode.medium.dynamic;

import leetcode.util.ResultCheck;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 曹云 on 2020/8/26.
 * 300. 最长上升子序列
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/
 */
public class LongestIncreasingSubsequence {

	public int lengthOfLIS1(int[] nums) {
		if (nums.length <= 1) return nums.length;
		List<List<Integer>> resultList = new ArrayList<>();
		int max = 0;
		for (int i=0;i<nums.length;i++) {
			boolean add = false;
			int num = nums[i];
			for (List<Integer> res: resultList) {
				if (res.get(res.size() - 1) < nums[i]) {
					res.add(nums[i]);
					max = Math.max(max, res.size());
					add = true;
				} else if (res.get(res.size() - 1) > nums[i]) {
					if (res.size() > 1 && res.get(res.size() - 2) < nums[i]) {
						res.set(res.size() - 1, nums[i]);
						add = true;
					}
				}
			}
			if (!add) {
				resultList.add(new ArrayList<Integer>(){{add(num);}});
				max = Math.max(max, 1);
			}
		}
		return max;
	}

	public int lengthOfLIS2(int[] nums) {
		int[] dp = new int[nums.length];
		int max = 0;
		for (int i=0; i<nums.length; i++) {
			int num = nums[i];
			dp[i] = 1;
			for (int j = i - 1; j>=0; j--) {
				if (nums[j] < num) {
					dp[i] =  Math.max(dp[i], dp[j] + 1);
				}
			}
			max = Math.max(max, dp[i]);
		}

		return max;
	}

	public int lengthOfLIS(int[] nums) {
		List<Integer> min = new ArrayList<>(nums.length + 1);
		min.add(0);
		int lenMax = 0;
		for (int num: nums) {
			if (lenMax == 0 || num > min.get(lenMax)) {
				min.add(num);
				lenMax++;
				continue;
			}
			int left = 1, right = lenMax + 1;
			while(left < right) {
				int mid = (left + right)/ 2;
				if (num > min.get(mid)) {
					left = mid + 1;
				} else {
					right = mid;
				}
			}

			if (min.get(left) > num) {
				min.set(left, num);
			}
		}

		return lenMax;
	}

	public static void main(String[] args){
		LongestIncreasingSubsequence main = new LongestIncreasingSubsequence();
		ResultCheck.check(main.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}), 4);
		ResultCheck.check(main.lengthOfLIS(new int[]{3,5,6,2,5,4,19,5,6,7,12}), 6);
		ResultCheck.check(main.lengthOfLIS(new int[]{1,3,6,7,9,4,10,5,6}), 6);
	}
}
