package leetcode.medium.backtrack;

import com.google.common.collect.Sets;
import leetcode.util.ResultCheck;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 曹云 on 2020/8/21.
 * 22. 括号生成
 * https://leetcode-cn.com/problems/generate-parentheses/solution/
 */
public class GenerateParentheses {

	public List<String> generateParenthesis1(int n) {
		List<String> result = new ArrayList<>();
		if (n == 0) {
			return new ArrayList<>();
		}
		backtrack("", 2 * n, result);
		return result;
	}

	private void backtrack(String combination, int n, List<String> result) {
		if (n == 0) {
			if (validParentheses(combination))
				result.add(combination);
			return;
		}
		n--;
		backtrack(combination + "(", n, result);
		backtrack(combination + ")", n, result);
	}

	private boolean validParentheses(String s) {
		int n =0;
		for (int i=0;i<s.length();i++) {
			if (s.charAt(i) == '(')
				n++;
			else
				n--;
			if (n<0)
				return false;
		}
		return n==0;
	}

	public List<String> generateParenthesis(int n) {
		List<String> result = new ArrayList<>();
		backtrack(new StringBuilder(), n, 0, 0, result);
		return result;
	}

	private void backtrack(StringBuilder out, int max, int left, int right, List<String> result) {
		if (out.length() == max * 2) {
			String str = out.toString();
//			if (validParentheses(str))
			result.add(str);
			return;
		}
		if (left < right)
			return;
		if (left < max) {
			out.append('(');
			backtrack(out, max, left + 1, right, result);
			out.deleteCharAt(out.length() -1);
		}
		if (right < max) {
			out.append(')');
			backtrack(out, max, left, right + 1, result);
			out.deleteCharAt(out.length() -1);
		}
	}

	public static void main(String[] args){
		GenerateParentheses main = new GenerateParentheses();
		ResultCheck.checkList(main.generateParenthesis(3), Sets.newHashSet(
				"((()))",
				"(()())",
				"(())()",
				"()(())",
				"()()()"
		));
	}
}
