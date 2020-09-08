package leetcode.hard.backtrack;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import leetcode.util.ResultCheck;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 曹云 on 2020/9/6.
 * 212. 单词搜索 II
 * https://leetcode-cn.com/problems/word-search-ii/solution/
 */
public class WordSearchII {
	public List<String> findWords1(char[][] board, String[] words) {
		List<String> result = new ArrayList<>();
		for (String word: words) {
			boolean r = false;
			char[] chars = word.toCharArray();
			for (int i=0; i<board.length; i++) {
				for (int j=0; j<board[i].length; j++) {
					r = dfs(board, chars, i, j, 0);
					if (r)
						break;
				}
				if (r)
					break;
			}
			if (r)
				result.add(word);
		}
		return result;
	}

	private boolean dfs(char[][] board, char[] word, int i, int j, int charIdx) {
		if (board[i][j] != word[charIdx])
			return false;
		if (charIdx == word.length - 1)
			return true;

		char tmp = board[i][j];
		board[i][j] = '\n';
		boolean up = i > 0 && dfs(board, word, i - 1, j, charIdx + 1);
		boolean left = !up && j > 0 && dfs(board, word, i, j - 1, charIdx + 1);
		boolean down = !left && !up && i < board.length - 1 && dfs(board, word, i + 1, j, charIdx + 1);
		boolean right = !left && !up && !down && j < board[i].length - 1 && dfs(board, word, i, j + 1, charIdx + 1);
		board[i][j] = tmp;
		return left || up || right || down;
	}

	public class Trie{
		public Trie[] links = new Trie[26];
		public String word = null;


		public Trie(){
		}


		public void insert(String word) {
			Trie node = this;
			for (char c : word.toCharArray()) {
				if (node.links[c - 'a'] == null) {
					node.links[c - 'a'] = new Trie();
				}
				node = node.links[c - 'a'];
			}
			node.word = word;
		}
		public Trie searchNode(char[] chars) {
			Trie node = this;
			for (char c : chars) {
				if (node.links[c-'a'] != null)
					node = node.links[c-'a'];
				else
					return null;
			}
			return node;
		}

		public boolean search(String word) {
			Trie node = searchNode(word.toCharArray());
			return node != null && node.word != null;
		}

		public boolean searchPrefix(String prefix) {
			Trie node = searchNode(prefix.toCharArray());
			return node != null;
		}

		public boolean containsKey(char c) {
			return this.links[c - 'a'] != null;
		}
		public Trie getChild(char c){
			return this.links[c - 'a'];
		}

	}

	public Trie createTrie() {
		return new Trie();
	}

	public List<String> findWords(char[][] board, String[] words) {
		List<String> result = new ArrayList<>();
		Trie trie = this.createTrie();
		for (String word: words) {
			trie.insert(word);
		}

		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[i].length; j++) {
				dfs(board, i, j, trie, result);
			}
		}
		return result;
	}

	private void dfs(char[][] board, int i, int j, Trie trie, List<String> result) {
		if (i < 0 || j < 0 || i > board.length - 1 || j > board[i].length - 1)
			return;
		char c = board[i][j];
		if (c == '\n')
			return;
		if (!trie.containsKey(c))
			return;
		Trie node = trie.getChild(c);
		if (node.word != null) {
			result.add(node.word);
			node.word = null;
		}

		board[i][j] = '\n';

		dfs(board, i, j-1, node, result);
		dfs(board, i, j+1, node, result);
		dfs(board, i - 1, j, node, result);
		dfs(board, i + 1, j, node, result);
		board[i][j] = c;
	}

	public static void main(String[] args){
		WordSearchII main = new WordSearchII();
		char[][] board = new char[][]{
				{'o', 'a', 'a', 'n'},
				{'e', 't', 'a', 'e'},
				{'i', 'h', 'k', 'r'},
				{'i', 'f', 'l', 'v'}
		};
		String[] words = new String[]{"oath", "pea", "eat", "rain"};
		ResultCheck.checkList(main.findWords(board, words), Sets.newHashSet("eat", "oath"));
		char[][] boardII = new char[][]{
				{'a', 'b'}
		};
		String[] wordsII = new String[]{"ba"};
		ResultCheck.checkList(main.findWords(boardII, wordsII), Sets.newHashSet("ba"));
		char[][] boardIII = new char[][]{
				{'a', 'a'}
		};
		String[] wordsIII = new String[]{"a"};
		ResultCheck.checkList(main.findWords(boardIII, wordsIII), Sets.newHashSet("a"));
		char[][] boardIV = new char[][]{
				{'a', 'b'},
				{'c', 'd'},
		};
		String[] wordsIV = new String[]{"ab","cb","ad","bd","ac","ca","da","bc","db","adcb","dabc","abb","acb"};
		ResultCheck.checkList(main.findWords(boardIV, wordsIV), Sets.newHashSet("ab","ac","bd","ca","db"));
		char[][] boardV = new char[][]{
				{'a', 'b'},
				{'a', 'a'},
		};
		String[] wordsV = new String[]{"aba","baa","bab","aaab","aaa","aaaa","aaba"};
		ResultCheck.checkList(main.findWords(boardV, wordsV), Sets.newHashSet("aaa","aaab","aaba","aba","baa"));
		char[][] boardVI = new char[][]{
				{'b','a','a','b','a','b'},
				{'a','b','a','a','a','a'},
				{'a','b','a','a','a','b'},
				{'a','b','a','b','b','a'},
				{'a','a','b','b','a','b'},
				{'a','a','b','b','b','a'},
				{'a','a','b','a','a','b'}
		};
		String[] wordsVI = new String[]{"aab","aabbbbabbaababaaaabababbaaba","abaabbbaaaaababbbaaaaabbbaab","ababaababaaabbabbaabbaabbaba"};
		ResultCheck.checkList(main.findWords(boardVI, wordsVI), Sets.newHashSet("aab","aabbbbabbaababaaaabababbaaba","abaabbbaaaaababbbaaaaabbbaab","ababaababaaabbabbaabbaabbaba"));
	}
}
