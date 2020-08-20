package leetcode.medium.arrayandstr;

import leetcode.util.ResultCheck;

import java.util.*;

/**
 * Created by 曹云 on 2020/8/18.
 * 3. Longest Substring Without Repeating Characters
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/
 */
public class LongestSubstrWithoutRpChars {

	public int lengthOfLongestSubstring1(String s) {
		int max = 0, rp = 0;
		Set<Character> set = new HashSet<>();
		for (int i=-1; i<s.length() && rp < s.length(); i++) {
			if (i >= 0) {
				set.remove(s.charAt(i));
			}
			while(rp < s.length() && !set.contains(s.charAt(rp))) {
				set.add(s.charAt(rp));
				rp++;
			}
			max = Math.max(max, rp - i - 1);
		}
		return max;
	}

	public int lengthOfLongestSubstring2(String s) {
		int max = 0, start = 0;
		Map<Character, Integer> map = new HashMap<>();
		for (int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			int prevIdx = map.getOrDefault(c, -1);
			start = Math.max(start ,prevIdx + 1);
			map.put(s.charAt(i), i);
			max = Math.max(max, i - start + 1);
		}
		return max;
	}
	public int lengthOfLongestSubstring(String s) {
		int max = 0, start = 0;
		int[] idx = new int[128];
		Arrays.fill(idx, -1);
		for (int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			int prevIdx = idx[c];
			start = Math.max(start ,prevIdx + 1);
			idx[c] = i;
			max = Math.max(max, i - start + 1);
		}
		return max;
	}
	public static void main(String[] args){
		LongestSubstrWithoutRpChars main = new LongestSubstrWithoutRpChars();
		ResultCheck.check(main.lengthOfLongestSubstring("abcabcbb"),3);
		ResultCheck.check(main.lengthOfLongestSubstring("bbbbb"),1);
		ResultCheck.check(main.lengthOfLongestSubstring("pwwkew"),3);
	}
}
