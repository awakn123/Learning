package leetcode.easy.other;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/17.
 * 20. Valid Parentheses
 * https://leetcode.com/problems/valid-parentheses/
 */
public class ValidParentheses {

	public boolean isValid(String s) {
		return false;
	}

	public static void main(String[] args){
		ValidParentheses main = new ValidParentheses();
		ResultCheck.check(main.isValid("()"), true);
		ResultCheck.check(main.isValid("()[]{}"), true);
		ResultCheck.check(main.isValid("(]"), false);
		ResultCheck.check(main.isValid("([)]"), false);
		ResultCheck.check(main.isValid("{[]}"), true);
	}
}
