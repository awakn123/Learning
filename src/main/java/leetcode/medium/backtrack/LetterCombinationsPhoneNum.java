package leetcode.medium.backtrack;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.List;

/**
 * Created by 曹云 on 2020/8/18.
 * 17. 电话号码的字母组合
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/solution/
 */
public class LetterCombinationsPhoneNum {

	public List<String> letterCombinations(String digits) {
		return null;
	}

	public static void main(String[] args){
		LetterCombinationsPhoneNum main = new LetterCombinationsPhoneNum();
		ResultCheck.checkList(main.letterCombinations("23"), Lists.newArrayList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"));
	}
}
