package leetcode.easy.string;

import com.alibaba.druid.sql.visitor.functions.Char;
import leetcode.util.ResultCheck;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by 曹云 on 2020/8/6.
 * 387. First Unique Character in a String
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 */
public class FirstUniChar {
	/**
	 * Space complexity : \mathcal{O}(1)O(1) because English alphabet contains 26 letters.
	 * I really didn't recognize it.
	 * @param s
	 * @return
	 */
	public int firstUniqChar(String s) {
		Map<Character, Integer> uniqCharIdx = new HashMap();
		Set<Character> duplCharSet = new HashSet<>();
		for (int i=0;i<s.length();i++) {
			char c = s.charAt(i);
			if (duplCharSet.contains(c)) {
				continue;
			} else if (uniqCharIdx.containsKey(c)) {
				uniqCharIdx.remove(c);
				duplCharSet.add(c);
			} else {
				uniqCharIdx.put(c, i);
			}
		}
		int i = s.length();
		for (int idx: uniqCharIdx.values()) {
			if (i>idx) {
				i = idx;
			}
		}
		return i == s.length() ? -1:i;
	}

	public static void main(String[] args){
		FirstUniChar main = new FirstUniChar();
		String sI = "leetcode";
		int answerI = 0;
		int resultI = main.firstUniqChar(sI);
		ResultCheck.check(resultI, answerI);
		String sII = "loveleetcode";
		int answerII = 2;
		int resultII = main.firstUniqChar(sII);
		ResultCheck.check(resultII, answerII);
	}
}
