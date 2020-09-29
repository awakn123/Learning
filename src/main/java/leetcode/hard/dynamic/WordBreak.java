package leetcode.hard.dynamic;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.List;

/**
 * Created by 曹云 on 2020/9/16.
 * 139. 单词拆分
 * https://leetcode-cn.com/problems/word-break/solution/
 */
public class WordBreak {
	boolean[] dp;
	int maxLength;

	public boolean wordBreak(String s, List<String> wordDict) {
		maxLength = 0;
		char[] chars = s.toCharArray();
		dp = new boolean[chars.length];
		Trie trie = new Trie();
		for (String word: wordDict) {
			trie.addWord(word);
			maxLength = Math.max(maxLength, word.length());
		}

		check(chars, trie, 0);
		for (int i=0; i<dp.length;i++) {
			if (dp[i]) {
				check(chars, trie, i+1);
			}
		}

		return dp[chars.length - 1];
	}

	private void check(char[] chars, Trie trie, int start) {
		if (start < 0 || start >= chars.length)
			return;
		Trie node = trie;
		int i = start;
		while (node != null && i < chars.length) {
			char c = chars[i];
			int idx = c - 'a';
			node = node.children[idx];
			if (node != null) {
				if (node.isEnd) {
					dp[i] = true;
				}
				i++;
			}
		}
	}

	class Trie{
		Trie[] children = new Trie[26];
		boolean isEnd = false;
		public Trie(){};
		public void addWord(String word) {
			Trie node = this;
			for (char c: word.toCharArray()) {
				int i = c - 'a';
				if (node.children[i] == null) {
					node.children[i] = new Trie();
				}
				node = node.children[i];
			}
			node.isEnd = true;
		}
	}

	public static void main(String[] args){
		WordBreak main = new WordBreak();
		ResultCheck.check(main.wordBreak("leetcode", Lists.newArrayList("leet", "code")), true);
		ResultCheck.check(main.wordBreak("applepenapple", Lists.newArrayList("apple", "pen")), true);
		ResultCheck.check(main.wordBreak("catsandog", Lists.newArrayList("cats", "dog", "sand", "and", "cat")), false);
		ResultCheck.check(main.wordBreak("bb", Lists.newArrayList("a", "b", "bbb", "bbbb")), true);
		ResultCheck.check(main.wordBreak("abcd", Lists.newArrayList("a", "abc", "b", "cd")), true);
	}
}
