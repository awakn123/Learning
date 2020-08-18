package leetcode.medium.arrayandstr;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/18.
 * 5. Longest Palindromic Substring
 * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/
 */
public class LongestPalindromicSubstr {

	public String longestPalindrome(String s) {
		return null;
	}

	public static void main(String[] args){
		LongestPalindromicSubstr main = new LongestPalindromicSubstr();
		ResultCheck.check(main.longestPalindrome("babd"), "bab");
		ResultCheck.check(main.longestPalindrome("cbbd"), "bb");
	}
}
