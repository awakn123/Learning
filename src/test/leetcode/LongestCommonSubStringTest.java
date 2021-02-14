package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by 曹云 on 2021/2/14.
 */
public class LongestCommonSubStringTest {
	@Test
	void test(){
		LongestCommonSubString lcs = new LongestCommonSubString();
		Assertions.assertEquals("a", lcs.LongestCommonSubString("abcde", "ace"));
		Assertions.assertEquals("abc", lcs.LongestCommonSubString("abc", "abc"));
		Assertions.assertEquals("", lcs.LongestCommonSubString("abc", "def"));
		Assertions.assertEquals("ofu", lcs.LongestCommonSubString("hofubmnylkra", "pqhgxgdofucvmr"));
		Assertions.assertEquals("b", lcs.LongestCommonSubString("bsbininm", "jmjkbkjkv"));
	}
}
