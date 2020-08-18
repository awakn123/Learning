package leetcode.medium.arrayandstr;

import leetcode.easy.string.LongestCommonPrefix;
import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/18.
 * 3. Longest Substring Without Repeating Characters
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/
 */
public class LongestSubstrWithoutRpChars {

	public int lengthOfLongestSubstring(String s) {
		return 0;
	}

	public static void main(String[] args){
		LongestSubstrWithoutRpChars main = new LongestSubstrWithoutRpChars();
		ResultCheck.check(main.lengthOfLongestSubstring("abcabcbb"),3);
		ResultCheck.check(main.lengthOfLongestSubstring("bbbbb"),1);
		ResultCheck.check(main.lengthOfLongestSubstring("pwwkew"),3);
	}
}
