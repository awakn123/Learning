package leetcode.hard.backtrack;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 曹云 on 2020/9/6.
 * 131. 分割回文串
 * https://leetcode-cn.com/problems/palindrome-partitioning/solution/
 */
public class PalindromePartitioning {
	List<List<String>> result;
	public List<List<String>> partition1(String s) {
	  	result = new ArrayList<>();
		backtrack1(s.toCharArray(), 0, new ArrayList<>());
		return result;
	}

	public void backtrack1(char[] chars, int first, List<String> palins) {
		if (first == chars.length) {
			result.add(new ArrayList<>(palins));
			return;
		}

		StringBuilder out = new StringBuilder();
		for (int i = first; i < chars.length; i++) {
			out.append(chars[i]);
			if (check(out)) {
				palins.add(out.toString());
				backtrack1(chars, i+1, palins);
				palins.remove(palins.size() - 1);
			}
		}
	}

	private boolean check(StringBuilder out) {
		int left = 0, right = out.length() - 1;
		while (left<right) {
			if (out.charAt(left) != out.charAt(right))
				return false;
			left++;
			right--;
		}
		return true;
	}
	public List<List<String>> partition(String s) {
		result = new ArrayList<>();

		char[] chars = s.toCharArray();
		boolean[][] isPalindrome = new boolean[chars.length + 1][];
		for (int l = 1; l <= chars.length; l++) {
			isPalindrome[l] = new boolean[chars.length];
			for (int i=0; i <= chars.length - l; i++) {
				if (l == 1)
					isPalindrome[l][i] = true;
				else if (l == 2)
					isPalindrome[l][i] = chars[i] == chars[i+1];
				else
					isPalindrome[l][i] = isPalindrome[l-2][i+1] && chars[i] == chars[i + l - 1];
			}
		}

		backtrack(s.toCharArray(), 0, new ArrayList<>(), isPalindrome);
		return result;
	}

	public void backtrack(char[] chars, int first, List<String> palins, boolean[][] isPalindrome) {
		if (first == chars.length) {
			result.add(new ArrayList<>(palins));
			return;
		}

		StringBuilder out = new StringBuilder();
		for (int i = first; i < chars.length; i++) {
			out.append(chars[i]);
			if (isPalindrome[out.length()][i - out.length() + 1]) {
				palins.add(out.toString());
				backtrack(chars, i+1, palins, isPalindrome);
				palins.remove(palins.size() - 1);
			}
		}
	}


	public static void main(String[] args){
		PalindromePartitioning main = new PalindromePartitioning();
		ResultCheck.checkTwoDimension(main.partition("aab"), Lists.newArrayList(
				Lists.newArrayList("aa", "b"),
				Lists.newArrayList("a", "a", "b")
		));
	}
}
