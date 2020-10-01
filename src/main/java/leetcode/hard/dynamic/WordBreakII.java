package leetcode.hard.dynamic;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import leetcode.util.ResultCheck;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 曹云 on 2020/9/16.
 * 140. Word Break II
 * https://leetcode-cn.com/problems/word-break-ii/solution/
 */
public class WordBreakII {
	List<String> result;
	boolean[] mark;
	StringBuilder out;

	public List<String> wordBreak(String s, List<String> wordDict) {
		// construct a 2d array, startPos in s -> all match wordList.
		List<Integer>[] startPosList = new ArrayList[s.length()];
		for (int i=0;i<wordDict.size();i++) {
			String word = wordDict.get(i);
			int j = s.indexOf(word);
			while (j != -1) {
				if (startPosList[j] == null)
					startPosList[j] = new ArrayList<>();
				startPosList[j].add(i);
				j = s.indexOf(word, j + 1);
			}
		}

		mark = new boolean[s.length()];
		out = new StringBuilder();
		result = new ArrayList<>();
		backtrack(startPosList, 0, wordDict);
		return result;
	}

	private void backtrack(List<Integer>[] startPosList, int start, List<String> wordDict) {
		if (start == startPosList.length) {
			result.add(out.toString());
			return;
		}
		if (mark[start])
			return;
		List<Integer> list = startPosList[start];
		if (list == null)
			return;
		int resultSize = result.size();
		for (Integer i: list) {
			String word = wordDict.get(i);
			if (start != 0) {
				out.append(" ");
			}
			out.append(word);
			backtrack(startPosList, start + word.length(), wordDict);
			out.delete(out.length() - word.length(), out.length());
			if (start != 0) {
				out.deleteCharAt(out.length() - 1);
			}
		}
		if (resultSize == result.size()) {
			mark[start] = true;
		}
	}

	public List<String> wordBreak1(String s, List<String> wordDict) {
		Trie trie = new Trie();
		for (String word: wordDict) {
			trie.addWord(word);
		}
		char[] chars = s.toCharArray();
		List<StringBuilder>[] dp = new ArrayList[chars.length];
		search(chars, 0, trie, dp);
		for (int i=0; i<dp.length; i++) {
			if (dp[i] != null) {
				search(chars, i + 1, trie, dp);
			}
		}
		List<String> result = new ArrayList<>();
		if (dp[chars.length - 1] != null) {
			for (StringBuilder out: dp[chars.length - 1]) {
				result.add(out.toString());
			}
		}
		return result;
	}

	private void search(char[] chars, int start, Trie trie, List<StringBuilder>[] dp) {
		Trie node = trie;
		StringBuilder out = new StringBuilder();
		for (int i = start; i < chars.length; i++) {
			char c = chars[i];
			node = node.children[c - 'a'];
			if (node == null) {
				break;
			} else {
				out.append(c);
				if (node.isEnd) {
					if (dp[i] == null) {
						dp[i] = new ArrayList<>();
					}
					if (start == 0) {
						dp[i].add(new StringBuilder(out));
					} else {
						for(StringBuilder dpout : dp[start - 1]) {
							StringBuilder curout = new StringBuilder(dpout);
							curout.append(' ').append(out);
							dp[i].add(curout);
						}
					}
				}
			}
		}
	}

	class Trie {
		Trie[] children = new Trie[26];
		boolean isEnd = false;
		public void addWord(String word) {
			Trie node = this;
			for (char c: word.toCharArray()) {
				if (node.children[c-'a'] == null) {
					node.children[c-'a'] = new Trie();
				}
				node = node.children[c-'a'];
			}
			node.isEnd = true;
		}
	}

	public static void main(String[] args){
		WordBreakII main = new WordBreakII();
		ResultCheck.checkList(main.wordBreak("catsanddog", Lists.newArrayList("cat", "cats", "and", "sand", "dog")), Sets.newHashSet("cats and dog", "cat sand dog"));
		ResultCheck.checkList(main.wordBreak("pineapplepenapple", Lists.newArrayList("apple", "pen", "applepen", "pine", "pineapple")),
				Sets.newHashSet("pine apple pen apple", "pineapple pen apple", "pine applepen apple"));
		ResultCheck.checkList(main.wordBreak("catsandog", Lists.newArrayList("cat", "cats", "and", "sand", "dog")), Sets.newHashSet());
	}
}
