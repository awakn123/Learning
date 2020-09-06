package leetcode.hard.backtrack;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.List;

/**
 * Created by 曹云 on 2020/9/6.
 * 131. 分割回文串
 * https://leetcode-cn.com/problems/palindrome-partitioning/solution/
 */
public class PalindromePartitioning {
	public List<List<String>> partition(String s) {
		return null;
	}

	public static void main(String[] args){
		PalindromePartitioning main = new PalindromePartitioning();
		ResultCheck.checkTwoDimension(main.partition("aab"), Lists.newArrayList(
				Lists.newArrayList("aa", "b"),
				Lists.newArrayList("a", "a", "b")
		));
	}
}
