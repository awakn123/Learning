package leetcode.medium.arrayandstr;

import leetcode.util.ResultCheck;

import java.util.Arrays;

/**
 * Created by 曹云 on 2020/8/18.
 * 5. Longest Palindromic Substring
 * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/
 */
public class LongestPalindromicSubstr {

	public String longestPalindrome1(String s) {
		if (s == null || s.length() < 2) return s;
		int[][] even = new int[s.length()][];
		int[][] odd = new int[s.length()][];
		for (int i = 0; i < s.length() - 1; i++) {
			int p = i, q = i + 1;
			if (s.charAt(p) == s.charAt(q)) {
				do {
					p--;
					q++;
				} while (p >= 0 && q < s.length() && s.charAt(p) == s.charAt(q));
				even[i] = new int[]{p + 1, q};
			}
			p = i;
			q = i + 2;
			if (q < s.length() && s.charAt(p) == s.charAt(q)) {
				do {
					p--;
					q++;
				} while (p >= 0 && q < s.length() && s.charAt(p) == s.charAt(q));
				odd[i] = new int[]{p + 1, q};
			}
		}

		int max = 1;
		int[] maxIdx = null;
		for (int i = 0; i < even.length; i++) {
			if (even[i] != null) {
				int len = even[i][1] - even[i][0];
				if (max < len) {
					maxIdx = even[i];
					max = len;
				}
			}
		}
		for (int i = 0; i < odd.length; i++) {
			if (odd[i] != null) {
				int len = odd[i][1] - odd[i][0];
				if (max < len) {
					maxIdx = odd[i];
					max = len;
				}
			}
		}
		if (maxIdx != null) {
			return s.substring(maxIdx[0], maxIdx[1]);
		} else
			return s.substring(0, 1);
	}

	public String longestPalindrome2(String s) {
		int[][] palin = new int[s.length() + 1][];
		int start = 0, end = 0;
		// len 为长度
		for (int len = 1; len <= s.length(); len++) {
			palin[len] = new int[s.length()];
			// i 为起始点
			for (int i = 0; i < s.length() - len + 1; i++) {
				if (len == 1) {
					palin[len][i] = 1;
				} else if (len == 2) {
					if (s.charAt(i) == s.charAt(i + 1)) {
						palin[len][i] = 1;
					} else {
						palin[len][i] = 0;
					}
				} else {
					if (palin[len - 2][i + 1] == 1 && s.charAt(i) == s.charAt(i + len - 1)) {
						palin[len][i] = 1;
					} else {
						palin[len][i] = 0;
					}
				}
				if (palin[len][i] == 1 && len > end - start) {
					start = i;
					end = i + len;
				}
			}
		}
		return s.substring(start, end);
	}
	public String longestPalindrome(String s) {
		if (s.length() == 0) return s;
		String result = "";
		for (int i=0; i<s.length(); i++) {
			// odd
			int start = i, end = i;
			while(start>=0 && end<s.length() && s.charAt(start) == s.charAt(end)) {
				start--;
				end++;
			}
			start++;
			end--;
			if (end - start >= result.length()) {
				result = s.substring(start, end + 1);
			}
			// even
			start = i;
			end = i + 1;
			while(start>=0 && end<s.length() && s.charAt(start) == s.charAt(end)) {
				start--;
				end++;
			}
			start++;
			end--;
			if (end - start >= result.length()) {
				result = s.substring(start, end + 1);
			}
		}
		return result;
	}
	public static void main(String[] args) {
		LongestPalindromicSubstr main = new LongestPalindromicSubstr();
		ResultCheck.check(main.longestPalindrome("babd"), "bab");
		ResultCheck.check(main.longestPalindrome("cbbd"), "bb");
		ResultCheck.check(main.longestPalindrome("abcda"), "a");
		ResultCheck.check(main.longestPalindrome("ccc"), "ccc");
	}
}
