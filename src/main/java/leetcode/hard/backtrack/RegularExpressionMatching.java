package leetcode.hard.backtrack;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/9/6.
 * 10. 正则表达式匹配
 * https://leetcode-cn.com/problems/regular-expression-matching/solution/
 */
public class RegularExpressionMatching {
	public boolean isMatch(String s, String p) {
		return false;
	}

	public static void main(String[] args){
		RegularExpressionMatching main = new RegularExpressionMatching();
		ResultCheck.check(main.isMatch("aa", "a"), false);
		ResultCheck.check(main.isMatch("aa", "a*"), true);
		ResultCheck.check(main.isMatch("ab", ".*"), true);
		ResultCheck.check(main.isMatch("aab", "c*a*b"), false);
		ResultCheck.check(main.isMatch("mississippi", "mis*is*p*."), false);
	}
}
