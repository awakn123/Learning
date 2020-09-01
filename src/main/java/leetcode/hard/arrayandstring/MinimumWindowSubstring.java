package leetcode.hard.arrayandstring;

import leetcode.util.ResultCheck;

import java.util.*;

/**
 * Created by 曹云 on 2020/8/30.
 * 76. 最小覆盖子串
 * https://leetcode-cn.com/problems/minimum-window-substring/solution/
 */
public class MinimumWindowSubstring {
	public String minWindow1(String s, String t) {
		Map<Character, Integer> tNum = new HashMap<>();
		for (char c: t.toCharArray())
			tNum.put(c, tNum.getOrDefault(c, 0) + 1);
		int start = 0;
		String res = "";
		Map<Character, Integer> sNum = new HashMap<>();
		for (int i=0; i<s.length(); i++) {
			sNum.put(s.charAt(i), sNum.getOrDefault(s.charAt(i), 0) + 1);
			if (check(tNum, sNum)) {
				while (check(tNum, sNum)) {
					char startc = s.charAt(start);
					sNum.put(startc, sNum.get(startc) - 1);
					start++;
				}
				start--;
				char startc = s.charAt(start);
				sNum.put(startc, sNum.get(startc) + 1);
				if ("".equals(res) || res.length() > (i+1-start))
					res = s.substring(start, i+1);
			}
		}
		return res;
	}

	private boolean check(Map<Character, Integer> tNum, Map<Character, Integer> sNum) {
		Iterator<Map.Entry<Character, Integer>> iter = tNum.entrySet().iterator();
		boolean pass = true;
		while(iter.hasNext()) {
			Map.Entry<Character, Integer> entry = iter.next();
			if (sNum.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
				pass = false;
				break;
			}
		}
		return pass;
	}
	public String minWindow(String s, String t) {
		Map<Character, Integer> tNum = new HashMap<>();
		for (char c: t.toCharArray()) {
			tNum.put(c, tNum.getOrDefault(c, 0) + 1);
		}
		int start = 0;
		char startChar = s.charAt(0);
		String res = "";
		int tSize = tNum.size();
		for (int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			if (!tNum.containsKey(c))
				continue;
			int n = tNum.getOrDefault(c, 0) - 1;
			tNum.put(c, n);
			if (n == 0)
				tSize--;
			if (tSize == 0 && (res.length() == 0 || startChar == c)) {
				while (tSize == 0) {
					startChar = s.charAt(start);
					start++;
					if (!tNum.containsKey(startChar))
						continue;
					n = tNum.get(startChar) + 1;
					tNum.put(startChar, n);
					if (n == 1)
						tSize++;
				}
				start--;
				startChar = s.charAt(start);
				tNum.put(startChar, n - 1);
				tSize--;
				if ("".equals(res) || res.length() > (i+1-start))
					res = s.substring(start, i+1);
			}
		}
		return res;
	}

	private boolean check(Map<Character, Integer> tNum) {
		Iterator<Map.Entry<Character,Integer>> iter = tNum.entrySet().iterator();
		while(iter.hasNext()) {
			if (iter.next().getValue() > 0)
				return false;
		}
		return true;
	}

	public static void main(String[] args){
		MinimumWindowSubstring main = new MinimumWindowSubstring();
		ResultCheck.check(main.minWindow("ADOBECODEBANC", "ABC"), "BANC");
		ResultCheck.check(main.minWindow("a", "a"), "a");
		ResultCheck.check(main.minWindow("a", "aa"), "");
	}
}
