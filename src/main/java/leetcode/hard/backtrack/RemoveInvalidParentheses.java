package leetcode.hard.backtrack;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.List;

/**
 * Created by 曹云 on 2020/9/6.
 * 301. 删除无效的括号
 * https://leetcode-cn.com/problems/remove-invalid-parentheses/solution/
 */
public class RemoveInvalidParentheses {
	public List<String> removeInvalidParentheses(String s) {
		return null;
	}

	public static void main(String[] args){
		RemoveInvalidParentheses main = new RemoveInvalidParentheses();
		ResultCheck.checkList(main.removeInvalidParentheses("()())()"), Lists.newArrayList("()()()", "(())()"));
		ResultCheck.checkList(main.removeInvalidParentheses("(a)())()"), Lists.newArrayList("(a)()()", "(a())()"));
		ResultCheck.checkList(main.removeInvalidParentheses(")("), Lists.newArrayList(""));
	}
}
