package leetcode.hard.backtrack;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/9/8.
 * 208. 实现 Trie (前缀树)
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/description/
 * 与 212. 单词搜索 II 是关联性题目。
 */
public class ImplTriePrefixTree {

	public class Trie{

		private Trie[] links;
		private boolean isEnd = false;
		/** Initialize your data structure here. */
		public Trie() {
			links = new Trie[26];

		}

		/** Inserts a word into the trie. */
		public void insert(String word) {
			Trie node = this;
			for (int i=0; i<word.length(); i++) {
				char c = word.charAt(i);
				if (node.links[c - 'a'] == null) {
					node.links[c - 'a'] = new Trie();
				}
				node = node.links[c - 'a'];
			}
			node.isEnd = true;
		}

		/** Returns if the word is in the trie. */
		public boolean search(String word) {
			Trie node = this;
			for (int i=0; i<word.length(); i++) {
				char c = word.charAt(i);
				if (node.links[c - 'a'] == null)
					return false;
				node = node.links[c - 'a'];
			}
			return node.isEnd;
		}

		/** Returns if there is any word in the trie that starts with the given prefix. */
		public boolean startsWith(String prefix) {
			Trie node = this;
			for (int i=0; i<prefix.length(); i++) {
				char c = prefix.charAt(i);
				if (node.links[c - 'a'] == null)
					return false;
				node = node.links[c - 'a'];
			}
			return true;
		}
	}

	public Trie createTrie() {
		return new Trie();
	}

	public static void main(String[] args){
		ImplTriePrefixTree main = new ImplTriePrefixTree();
		Trie node = main.createTrie();
		node.insert("apple");
		ResultCheck.check(node.search("apple"), true);
		ResultCheck.check(node.search("app"), false);
		ResultCheck.check(node.startsWith("app"), true);
		node.insert("app");
		ResultCheck.check(node.search("app"), true);
	}
}
