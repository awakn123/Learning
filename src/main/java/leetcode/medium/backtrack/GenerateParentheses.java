package leetcode.medium.backtrack;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import leetcode.util.ResultCheck;

import java.util.List;

/**
 * Created by 曹云 on 2020/8/21.
 * 22. 括号生成
 * https://leetcode-cn.com/problems/generate-parentheses/solution/
 */
public class GenerateParentheses {

	public List<String> generateParenthesis(int n) {
		return null;
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
