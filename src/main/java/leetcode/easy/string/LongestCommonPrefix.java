package leetcode.easy.string;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/6.
 * 14. Longest Common Prefix
 * https://leetcode.com/problems/longest-common-prefix/
 */
public class LongestCommonPrefix {
	public String longestCommonPrefix(String[] strs) {
		return "";
	}
	public static void main(String[] args){
		LongestCommonPrefix main = new LongestCommonPrefix();
		String[] strsI = {"flower", "flow", "flight"};
		String answerI = "fl";
		String resultI = main.longestCommonPrefix(strsI);
		ResultCheck.check(resultI, answerI);
		String[] strsII = {"dog","racecar","car"};
		String answerII = "";
		String resultII = main.longestCommonPrefix(strsII);
		ResultCheck.check(resultII, answerII);
	}
}
