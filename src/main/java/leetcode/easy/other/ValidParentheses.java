package leetcode.easy.other;

import leetcode.util.ResultCheck;

import java.util.LinkedList;

/**
 * Created by 曹云 on 2020/8/17.
 * 20. Valid Parentheses
 * https://leetcode.com/problems/valid-parentheses/
 */
public class ValidParentheses {

	public boolean isValid(String s) {
		char[] stack = new char[s.length()];
		int stackIdx = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch(c) {
				case '(':
				case '{':
				case '[':
					stack[stackIdx++] = c;
					break;
				case ')':
					if (--stackIdx < 0 || stack[stackIdx] != '(')
						return false;
					break;
				case '}':
					if (--stackIdx < 0 || stack[stackIdx] != '{')
						return false;
					break;
				case ']':
					if (--stackIdx < 0 || stack[stackIdx] != '[')
						return false;
					break;
			}
		}
		return stackIdx == 0;
	}

	public boolean isValid2(String s) {
		LinkedList<Character> stack = new LinkedList<>();
		for (char c : s.toCharArray()) {
			if (c == '[') stack.push(']');
			else if (c == '(') stack.push(')');
			else if (c == '{') stack.push('}');
			else if (stack.isEmpty() || c != stack.pop()) return false;
		}
		return stack.isEmpty();
	}

	public static void main(String[] args){
		ValidParentheses main = new ValidParentheses();
		ResultCheck.check(main.isValid("()"), true);
		ResultCheck.check(main.isValid("()[]{}"), true);
		ResultCheck.check(main.isValid("(]"), false);
		ResultCheck.check(main.isValid("([)]"), false);
		ResultCheck.check(main.isValid("{[]}"), true);
		ResultCheck.check(main.isValid("{"), false);
	}
}
