package leetcode.hard.arrayandstring;

import leetcode.util.ResultCheck;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 曹云 on 2020/8/30.
 * 128. 最长连续序列
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/solution/
 */
public class LongestConsecutiveSequence {
	public int longestConsecutive(int[] nums) {
		if (nums.length == 0) return 0;
		Set<Integer> numSet = new HashSet<>(nums.length);
		for (int num: nums)
			numSet.add(num);
		int maxLen = 1;
		for (int num: nums) {
			if (numSet.contains(num - 1))
				continue;
			int len = 1;
			while (numSet.contains(++num))
				len++;
			maxLen = Math.max(maxLen, len);
		}
		return maxLen;
	}

	public static void main(String[] args){
		LongestConsecutiveSequence main = new LongestConsecutiveSequence();
		ResultCheck.check(main.longestConsecutive(new int[]{100,4,200,1,3,2}),4);
	}
}
