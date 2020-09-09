package leetcode.hard.backtrack;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 曹云 on 2020/9/6.
 * 301. 删除无效的括号
 * https://leetcode-cn.com/problems/remove-invalid-parentheses/solution/
 */
public class RemoveInvalidParentheses {

	private Set<String> res;
	public List<String> removeInvalidParentheses(String s) {
		res = new HashSet<>();
		int leftWrong = 0, rightWrong = 0;
		char[] chars = s.toCharArray();
		for (int i=0; i<chars.length;i++) {
			char c = chars[i];
			if (c == '(') {
				leftWrong++;
			} else if (c == ')') {
				if (leftWrong == 0) {
					rightWrong++;
				} else {
					leftWrong--;
				}
			}
		}
		backtrack(chars, 0, 0, 0, leftWrong, rightWrong, new StringBuilder());

		return new ArrayList<>(res);
	}

	private void backtrack(char[] chars, int idx, int leftCount, int rightCount, int leftWrong, int rightWrong, StringBuilder out) {
		if (idx == chars.length) {
			if (leftWrong == 0 && rightWrong == 0)
				res.add(out.toString());
			return;
		}
		char c = chars[idx++];
		if (leftWrong > 0 && c == '(') {
			backtrack(chars, idx, leftCount, rightCount, leftWrong - 1, rightWrong, out);
		}
		if (rightWrong > 0 && c == ')') {
			backtrack(chars, idx, leftCount, rightCount, leftWrong, rightWrong - 1, out);
		}
		out.append(c);
		if (c != '(' && c != ')') {
			backtrack(chars, idx, leftCount, rightCount, leftWrong, rightWrong, out);
		} else if (c == '(') {
			backtrack(chars, idx, leftCount + 1, rightCount, leftWrong, rightWrong, out);
		} else if (leftCount > rightCount) {
			backtrack(chars, idx, leftCount, rightCount + 1, leftWrong, rightWrong, out);
		}
		out.deleteCharAt(out.length() - 1);
	}

	public static void main(String[] args){
		RemoveInvalidParentheses main = new RemoveInvalidParentheses();
		ResultCheck.checkList(main.removeInvalidParentheses("(())(("), Lists.newArrayList("(())"));
		ResultCheck.checkList(main.removeInvalidParentheses("()())()"), Lists.newArrayList("()()()", "(())()"));
		ResultCheck.checkList(main.removeInvalidParentheses("(a)())()"), Lists.newArrayList("(a)()()", "(a())()"));
		ResultCheck.checkList(main.removeInvalidParentheses(")("), Lists.newArrayList(""));
	}
}
