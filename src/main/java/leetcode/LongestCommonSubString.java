package leetcode;

/**
 * Created by 曹云 on 2021/2/14.
 */
public class LongestCommonSubString {
	public String LongestCommonSubString(String text1, String text2) {
		String result = "";
		int m = text1.length(), n = text2.length();
		char[] char1 = text1.toCharArray(), char2 = text2.toCharArray();
		String[][] dp = new String[m+1][n+1];
		for (int i=1; i<m+1; i++) {
			for (int j=1; j<n+1; j++) {
				if (char1[i-1] == char2[j-1]) {
					dp[i][j] = dp[i-1][j-1] == null ? Character.toString(char1[i-1]) : (dp[i-1][j-1] + Character.toString(char1[i-1]));
					if (dp[i][j].length() > result.length()) {
						result = dp[i][j];
					}
				}
			}
		}
		return result;
	}
}
