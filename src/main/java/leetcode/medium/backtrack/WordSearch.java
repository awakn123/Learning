package leetcode.medium.backtrack;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/21.
 * 79. 单词搜索
 * https://leetcode-cn.com/problems/word-search/solution/
 */
public class WordSearch {

	public boolean exist(char[][] board, String word) {
		return false;
	}
	public static void main(String[] args){
		WordSearch main = new WordSearch();
		char[][] board = new char[][]{
				new char[]{'A','B','C','E'},
				new char[]{'S','F','C','S'},
				new char[]{'A','D','E','E'},
		};
		ResultCheck.check(main.exist(board, "ABCCED"), true);
		ResultCheck.check(main.exist(board, "SEE"), true);
		ResultCheck.check(main.exist(board, "ABCB"), false);
	}
}
