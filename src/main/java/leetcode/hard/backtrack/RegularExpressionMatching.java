package leetcode.hard.backtrack;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/9/6.
 * 10. 正则表达式匹配
 * https://leetcode-cn.com/problems/regular-expression-matching/solution/
 */
public class RegularExpressionMatching {
	public boolean isMatch(String s, String p) {
		char[] schars = s.toCharArray(), pchars = p.toCharArray();
		int m = s.length(), n = p.length();
		boolean dp[][] = new boolean[m + 1][n + 1];
		dp[0][0] = true;
		// dp[i][0] false.


		for (int i=0; i<=m; i++) {
			for (int j=1; j<=n; j++) {
				if (pchars[j - 1] == '*') {
					dp[i][j] = dp[i][j - 2];
					if (check(schars, pchars, i - 1, j - 2)) {
						dp[i][j] = dp[i][j] || dp[i - 1][j];
					}
				} else {
					dp[i][j] = check(schars, pchars, i - 1, j - 1) && dp[i - 1][j - 1];
				}
			}
		}
		return dp[m][n];
	}

	private boolean check(char[] schars, char[] pchars, int i, int j) {
		if (i==-1)
			return false;
		return schars[i] == pchars[j] || pchars[j] == '.';
	}

	public static void main(String[] args){
		RegularExpressionMatching main = new RegularExpressionMatching();
		ResultCheck.check(main.isMatch("aa", "a"), false);
		ResultCheck.check(main.isMatch("aa", "a*"), true);
		ResultCheck.check(main.isMatch("ab", ".*"), true);
		ResultCheck.check(main.isMatch("aab", "c*a*b"), true);
		ResultCheck.check(main.isMatch("mississippi", "mis*is*p*."), false);
	}
}
