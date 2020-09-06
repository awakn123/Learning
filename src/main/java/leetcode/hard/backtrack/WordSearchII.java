package leetcode.hard.backtrack;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.List;

/**
 * Created by 曹云 on 2020/9/6.
 * 212. 单词搜索 II
 * https://leetcode-cn.com/problems/word-search-ii/solution/
 */
public class WordSearchII {
	public List<String> findWords(char[][] board, String[] words) {
		return null;
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
		ResultCheck.checkList(main.findWords(board, words), Lists.newArrayList("eat", "oath"));
	}
}
