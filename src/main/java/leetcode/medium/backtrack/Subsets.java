package leetcode.medium.backtrack;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 曹云 on 2020/8/21.
 * 78. 子集
 * https://leetcode-cn.com/problems/subsets/solution/
 */
public class Subsets {

	public List<List<Integer>> subsets1(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		for (int len=0; len<=nums.length; len++) {
			backtrack(nums, len, new ArrayList<>(), 0, result);
		}
		return result;
	}

	private void backtrack(int[] nums, int len, List<Integer> out, int first, List<List<Integer>> result) {
		if (out.size() == len) {
			result.add(new ArrayList<>(out));
			return;
		}
		for (int i=first; i<nums.length; i++) {
			out.add(nums[i]);
			backtrack(nums, len, out, i + 1, result);
			out.remove(out.size() - 1);
		}
	}

	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		int n = nums.length;
		for (int i=0;i<(int)Math.pow(2, n); i++) {
			List<Integer> out = new ArrayList<>();
			int combination = i;
			for (int j=0; j<n; j++) {
				if (combination%2 == 1) {
					out.add(nums[j]);
				}
				combination >>= 1;
			}
			result.add(out);
		}
		return result;
	}


	public static void main(String[] args){
		Subsets main = new Subsets();
		ResultCheck.checkTwoDimension(main.subsets(new int[]{1,2,3}), Lists.newArrayList(
				Lists.newArrayList(),
				Lists.newArrayList(1),
				Lists.newArrayList(2),
				Lists.newArrayList(3),
				Lists.newArrayList(1,2),
				Lists.newArrayList(1,3),
				Lists.newArrayList(2,3),
				Lists.newArrayList(1,2,3)
		));
	}
}
