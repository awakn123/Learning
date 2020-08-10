package leetcode.easy.string;

import leetcode.util.ResultCheck;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 曹云 on 2020/8/6.
 * 242. Valid Anagram
 * https://leetcode.com/problems/valid-anagram/
 */
public class ValidAnagram {

	public boolean isAnagram(String s, String t) {
		if (s == t) return true;
		if (s == null || t == null) return false;
		if (s.length() != t.length()) return false;
		if (s.equals(t)) return true;
		Map<Character, Integer> charNumMap = new HashMap();
		for (int i=0;i<s.length();i++) {
			charNumMap.put(s.charAt(i), charNumMap.getOrDefault(s.charAt(i),0) + 1);
		}
		for (int i=0;i<t.length();i++) {
			int num = charNumMap.getOrDefault(t.charAt(i), 0) - 1;
			if (num < 0)
				return false;
			charNumMap.put(t.charAt(i), num);
		}
		return true;
	}

	/**
	 * see and reimplement
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isAnagram2(String s, String t) {
		if (s == t) return true;
		if (s == null || t == null) return false;
		if (s.length() != t.length()) return false;
		if (s.equals(t)) return true;
		Map<Character, Integer> charNumMap = new HashMap();
		for (int i=0;i<s.length();i++) {
			charNumMap.put(s.charAt(i), charNumMap.getOrDefault(s.charAt(i),0) + 1);
			charNumMap.put(t.charAt(i), charNumMap.getOrDefault(t.charAt(i),0) - 1);
		}
		for (int n:charNumMap.values()) {
			if (n<0)
				return false;
		}
		return true;
	}

	public static void main(String[] args){
		ValidAnagram main = new ValidAnagram();
		String sI = "anagram";
		String tI = "nagaram";
		boolean answerI = true;
		boolean resultI = main.isAnagram2(sI, tI);
		ResultCheck.check(resultI, answerI);
		String sII = "rat";
		String tII = "car";
		boolean answerII = false;
		boolean resultII = main.isAnagram2(sII, tII);
		ResultCheck.check(resultII, answerII);
		String sIII = "aacc";
		String tIII = "ccac";
		boolean answerIII = false;
		boolean resultIII = main.isAnagram2(sIII, tIII);
		ResultCheck.check(resultIII, answerIII);
	}
}
