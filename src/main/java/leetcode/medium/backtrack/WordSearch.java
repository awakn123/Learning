package leetcode.medium.backtrack;

import leetcode.util.ResultCheck;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by 曹云 on 2020/8/21.
 * 79. 单词搜索
 * https://leetcode-cn.com/problems/word-search/solution/
 */
public class WordSearch {

	public boolean exist(char[][] board, String word) {
		for (int i=0; i<board.length; i++){
			for (int j=0; j<board[i].length; j++) {
				if (backtrack(board, word, i, j, 0))
					return true;
			}
		}
		return false;
	}

	private boolean backtrack(char[][] board, String word, int row, int col, int wordIdx) {
		if (row < 0 || row >= board.length)
			return false;
		if (col < 0 || col >= board[row].length)
			return false;
		char c = board[row][col];
		char w = word.charAt(wordIdx);
		if (board[row][col] == '\n')
			return false;
		if (c == w) {
			wordIdx++;
			if (wordIdx == word.length())
				return true;
		} else {
			return false;
		}
		board[row][col] = '\n';
		boolean r = false;
		if (backtrack(board, word, row - 1, col, wordIdx))
			r = true;
		else if (backtrack(board, word, row + 1, col, wordIdx))
			r = true;
		else if (backtrack(board, word, row, col - 1, wordIdx))
			r = true;
		else if (backtrack(board, word, row, col + 1, wordIdx))
			r = true;
		board[row][col] = c;
		wordIdx--;
		return r;
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
		char[][] boardII = new char[][]{
				new char[]{'A','B'},
				new char[]{'C','D'},
		};
		ResultCheck.check(main.exist(boardII, "ABCD"), false);
	}
}
