package leetcode.easy.string;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/6.
 * 125. Valid Palindrome
 * https://leetcode.com/problems/valid-palindrome/
 */
public class ValidPalindrome {

	public boolean isPalindrome(String s) {
		return false;
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
	}
}
