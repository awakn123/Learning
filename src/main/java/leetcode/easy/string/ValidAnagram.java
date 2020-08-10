package leetcode.easy.string;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/6.
 * 242. Valid Anagram
 * https://leetcode.com/problems/valid-anagram/
 */
public class ValidAnagram {

	public boolean isAnagram(String s, String t) {
		return false;
	}

	public static void main(String[] args){
		ValidAnagram main = new ValidAnagram();
		String sI = "anagram";
		String tI = "nagaram";
		boolean answerI = true;
		boolean resultI = main.isAnagram(sI, tI);
		ResultCheck.check(resultI, answerI);
		String sII = "rat";
		String tII = "car";
		boolean answerII = false;
		boolean resultII = main.isAnagram(sII, tII);
		ResultCheck.check(resultII, answerII);
	}
}
