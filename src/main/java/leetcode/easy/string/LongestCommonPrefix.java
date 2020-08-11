package leetcode.easy.string;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/6.
 * 14. Longest Common Prefix
 * https://leetcode.com/problems/longest-common-prefix/
 */
public class LongestCommonPrefix {
	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0) return "";
		if (strs.length == 1) return strs[0];
		StringBuilder out = new StringBuilder();
		for (int i=0; i<strs[0].length(); i++) {
			for (int j=1; j<strs.length; j++) {
				if (i >= strs[j].length() || strs[0].charAt(i) != strs[j].charAt(i))
					return out.toString();
			}
			out.append(strs[0].charAt(i));
		}
		return out.toString();
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
