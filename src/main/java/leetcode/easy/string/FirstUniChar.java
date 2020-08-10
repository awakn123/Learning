package leetcode.easy.string;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/6.
 * 387. First Unique Character in a String
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 */
public class FirstUniChar {
	public int firstUniqChar(String s) {
		return 0;
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
