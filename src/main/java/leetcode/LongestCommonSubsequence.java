package leetcode;

/**
 * Created by 曹云 on 2021/2/14.
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 */
public class LongestCommonSubsequence {
	public int longestCommonSubsequence(String text1, String text2) {
		int m = text1.length(), n = text2.length();
		char[] char1 = text1.toCharArray(), char2 = text2.toCharArray();
		int[][] dp = new int[m+1][n+1];
		for (int i=1; i<m+1; i++) {
			for (int j=1; j<n+1; j++) {
				if (char1[i-1] == char2[j-1])
					dp[i][j] = dp[i-1][j-1] + 1;
				else
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
		return dp[m][n];
	}
}
