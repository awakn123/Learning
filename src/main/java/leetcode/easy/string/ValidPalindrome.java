package leetcode.easy.string;

import leetcode.util.ResultCheck;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 曹云 on 2020/8/6.
 * 125. Valid Palindrome
 * https://leetcode.com/problems/valid-palindrome/
 */
public class ValidPalindrome {

	public boolean isPalindrome(String s) {
		int l = 0, r = s.length() - 1;
		while (l < r) {
			while (l < r && !Character.isLetterOrDigit(s.charAt(l))) l++;
			while (l < r && !Character.isLetterOrDigit(s.charAt(r))) r--;
			if (l < r && Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r)))
				return false;
			l++;
			r--;
		}
		return true;
	}

	public static void main(String[] args){
		ValidPalindrome main = new ValidPalindrome();
		String sI = "A man, a plan, a canal: Panama";
		boolean answerI = true;
		boolean resultI = main.isPalindrome(sI);
		ResultCheck.check(resultI, answerI);
		String sII = "race a car";
		boolean answerII = false;
		boolean resultII = main.isPalindrome(sII);
		ResultCheck.check(resultII, answerII);
		String sIII = "\"0P\"";
		boolean answerIII = false;
		boolean resultIII = main.isPalindrome(sIII);
		ResultCheck.check(resultIII, answerIII);
	}
}
