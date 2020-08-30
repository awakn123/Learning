package leetcode.hard.arrayandstring;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/30.
 * 76. 最小覆盖子串
 * https://leetcode-cn.com/problems/minimum-window-substring/solution/
 */
public class MinimumWindowSubstring {
	public String minWindow(String s, String t) {
		return s;
	}
	public static void main(String[] args){
		MinimumWindowSubstring main = new MinimumWindowSubstring();
		ResultCheck.check(main.minWindow("ADOBECODEBANC", "ABC"), "BANC");
	}
}
