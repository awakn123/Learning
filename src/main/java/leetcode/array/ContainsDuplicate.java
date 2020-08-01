package leetcode.array;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 曹云 on 2020/8/1.
 * 217.Contains Duplicate
 * https://leetcode.com/problems/contains-duplicate/
 */
public class ContainsDuplicate {
	/**
	 * O(n) extra space;
	 * @param nums
	 * @return
	 */
	public boolean containsDuplicate(int[] nums) {
		Set<Integer> numSet = new HashSet<Integer>();
		for (int i=0;i<nums.length;i++) {
			if (numSet.contains(nums[i])) {
				return true;
			}
			numSet.add(nums[i]);
		}
		return false;
	}


	/**
	 * sort then go through, didn't expect to write it.
	 * @param nums
	 * @return
	 */
	public boolean containsDuplicate2(int[] nums) {
		return false;
	}


	public static void main(String[] args){
		int[] numsI = {1,2,3,4};
		boolean answerI = false;
		int[] numsII = {1,2,3,2,4};
		boolean answerII = true;
		int[] numsIII = {1,1,2,2,3,4};
		boolean answerIII = true;
		ContainsDuplicate main = new ContainsDuplicate();
		boolean resultI = main.containsDuplicate2(numsI);
		boolean resultII = main.containsDuplicate2(numsII);
		boolean resultIII = main.containsDuplicate2(numsIII);
		checkAnswer(resultI, answerI);
		checkAnswer(resultII, answerII);
		checkAnswer(resultIII, answerIII);
	}

	public static void checkAnswer(boolean result, boolean answer) {
		System.out.println(result == answer ? "Right!" : "Wrong!");
	}
}
