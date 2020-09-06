package leetcode.hard.backtrack;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/9/6.
 * 44. 通配符匹配
 * https://leetcode-cn.com/problems/wildcard-matching/solution/
 */
public class WildCardMatching {
	public boolean isMatch(String s, String p) {
		return false;
	}

	public static void main(String[] args){
		WildCardMatching main = new WildCardMatching();
		ResultCheck.check(main.isMatch("aa", "a"), false);
		ResultCheck.check(main.isMatch("aa", "*"), true);
		ResultCheck.check(main.isMatch("cb", "?a"), false);
		ResultCheck.check(main.isMatch("adceb", "a*b"), true);
		ResultCheck.check(main.isMatch("acdcb", "a*c?b"), false);
	}
}
