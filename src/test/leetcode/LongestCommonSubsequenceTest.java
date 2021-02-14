package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by 曹云 on 2021/2/14.
 */
public class LongestCommonSubsequenceTest {
	@Test
	void test(){
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		Assertions.assertEquals(3, lcs.longestCommonSubsequence("abcde", "ace"));
		Assertions.assertEquals(3, lcs.longestCommonSubsequence("abc", "abc"));
		Assertions.assertEquals(0, lcs.longestCommonSubsequence("abc", "def"));
		Assertions.assertEquals(5, lcs.longestCommonSubsequence("hofubmnylkra", "pqhgxgdofcvmr"));
		Assertions.assertEquals(1, lcs.longestCommonSubsequence("bsbininm", "jmjkbkjkv"));
	}
}
