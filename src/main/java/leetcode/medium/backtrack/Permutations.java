package leetcode.medium.backtrack;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.*;

/**
 * Created by 曹云 on 2020/8/21.
 * 46. 全排列
 * https://leetcode-cn.com/problems/permutations/solution/
 */
public class Permutations {

	public List<List<Integer>> permute1(int[] nums) {
		return recurse(nums, nums.length - 1);
	}

	private List<List<Integer>> recurse(int[] nums, int i) {
		if (i < 0) return null;
		List<List<Integer>> prev = recurse(nums, i-1);
		List<List<Integer>> result = new ArrayList<>();
		if (prev == null) {
			LinkedList<Integer> r = new LinkedList<>();
			r.add(nums[i]);
			result.add(r);
		} else {
			for (List<Integer> pr : prev) {
				for (int j=0;j<=pr.size();j++) {
					LinkedList<Integer> r = new LinkedList<>(pr);
					r.add(j, nums[i]);
					result.add(r);
				}
			}
		}
		return result;
	}

	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> numList = new ArrayList<>(nums.length);
		for (int n: nums) {
			numList.add(n);
		}
		backtrack(numList, 0, result);
		return result;
	}

	private void backtrack(List<Integer> nums, int first, List<List<Integer>> result) {
		if (nums.size() == first) {
			result.add(new ArrayList<>(nums));
			return;
		}

		for (int i=first; i<nums.size(); i++) {
			Collections.swap(nums, i ,first);
			backtrack(nums, first + 1, result);
			Collections.swap(nums, first, i);
		}
	}

	public static void main(String[] args){
		Permutations main = new Permutations();
		ResultCheck.checkTwoDimension(main.permute(new int[]{1,2,3}),
				Lists.newArrayList(
						Lists.newArrayList(1,2,3),
						Lists.newArrayList(1,3,2),
						Lists.newArrayList(2,1,3),
						Lists.newArrayList(2,3,1),
						Lists.newArrayList(3,1,2),
						Lists.newArrayList(3,2,1)
						)
			);
	}
}
