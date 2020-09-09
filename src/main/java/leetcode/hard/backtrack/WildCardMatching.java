package leetcode.hard.backtrack;

import leetcode.util.ResultCheck;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 曹云 on 2020/9/6.
 * 44. 通配符匹配
 * https://leetcode-cn.com/problems/wildcard-matching/solution/
 */
public class WildCardMatching {
	public boolean isMatch1(String s, String p) {
		int m = s.length(), n = p.length();
		char[] scs = s.toCharArray(), pcs = p.toCharArray();
		boolean[][] dp = new boolean[m+1][n+1];
		for (int i=1;i<=n;i++) {
			if(pcs[i-1] == '*')
				dp[0][i] = true;
			else
				break;
		}
		dp[0][0] = true;
		for (int i=1; i<m+1; i++) {
			for (int j=1; j<n+1; j++) {
				if (pcs[j - 1] == '*') {
					dp[i][j] = dp[i][j-1] || dp[i-1][j];
				} else if (pcs[j - 1] == '?' || pcs[j-1] == scs[i-1]) {
					dp[i][j] = dp[i-1][j-1];
				}
			}
		}
		return dp[m][n];
	}

	public boolean isMatch(String s, String p) {
		char[] schars = s.toCharArray(), pchars = p.toCharArray();
		int m = schars.length, n = pchars.length;
		int i = 0, j = 0;
		while (i < m && j < n && pchars[j] != '*') {
			if (schars[i] == pchars[j] || pchars[j] == '?') {
				i++;
				j++;
			} else
				return false;
		}

		if (j == n)
			return i == m;
		i = 0;
		j = 0;
		int sRecord = -1, pRecord = -1;
		while (i < m) {
			if (j < n && pchars[j] == '*') {
				j++;
				sRecord = i;
				pRecord = j;
			} else if(j < n && (schars[i] == pchars[j] || pchars[j] == '?')) {
				i++;
				j++;
			} else if (pRecord >= 0 && pRecord <= n) {
				i = ++sRecord;
				j = pRecord;
			} else {
				return false;
			}
		}

		for (; j < n; j++) {
			if (pchars[j] != '*')
				return false;
		}
		return true;
	}


	public static void main(String[] args){
		WildCardMatching main = new WildCardMatching();
		ResultCheck.check(main.isMatch("aa", "aa*"), true);
		ResultCheck.check(main.isMatch("aaa", "a*"), true);
		ResultCheck.check(main.isMatch("aa", "a"), false);
		ResultCheck.check(main.isMatch("aa", "*"), true);
		ResultCheck.check(main.isMatch("cb", "?a"), false);
		ResultCheck.check(main.isMatch("adceb", "a*b"), true);
		ResultCheck.check(main.isMatch("acdcb", "a*c?b"), false);
		ResultCheck.check(main.isMatch("mississippi", "m??*ss*?i*pi"), false);
	}
}
