package leetcode.medium.backtrack;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 曹云 on 2020/8/18.
 * 17. 电话号码的字母组合
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/solution/
 */
public class LetterCombinationsPhoneNum {

	public List<String> letterCombinations1(String digits) {
		List<String> result = new ArrayList<>();
		for (int i=0; i<digits.length(); i++) {
			int n = Character.getNumericValue(digits.charAt(i));
			List<String> nextResult = new ArrayList<>();
			for (char c : toLetter(n)) {
				if (result.isEmpty()) {
					nextResult.add(Character.toString(c));
				} else {
					for (String s : result) {
						nextResult.add(s + c);
					}
				}
			}
			result = nextResult;
		}
		return result;
	}

	public List<String> letterCombinations(String digits) {
		List<String> result = new ArrayList<>();
		if(digits.length() == 0)
			return result;
		backtrack("", digits, result);
		return result;
	}

	private void backtrack(String combination, String digits, List<String> result) {
		if (digits.length() == 0) {
			result.add(combination);
			return;
		}
		char[] letters = toLetter(Character.getNumericValue(digits.charAt(0)));
		for (int i=0;i<letters.length;i++) {
			backtrack(combination + Character.toString(letters[i]), digits.substring(1), result);
		}
	}

	private char[] toLetter(int n) {
		switch(n) {
			case 2: return new char[]{'a', 'b', 'c'};
			case 3: return new char[]{'d', 'e', 'f'};
			case 4: return new char[]{'g', 'h', 'i'};
			case 5: return new char[]{'j', 'k', 'l'};
			case 6: return new char[]{'m', 'n', 'o'};
			case 7: return new char[]{'p', 'q', 'r', 's'};
			case 8: return new char[]{'t', 'u', 'v'};
			case 9: return new char[]{'w', 'x', 'y', 'z'};
			default: return null;
		}
	}


	public static void main(String[] args){
		LetterCombinationsPhoneNum main = new LetterCombinationsPhoneNum();
		ResultCheck.checkList(main.letterCombinations("23"), Lists.newArrayList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"));
	}
}
