package leetcode.hard.treeandgraph;

import java.util.Arrays;

/**
 * Created by 曹云 on 2020/9/3.
 * 130. 被围绕的区域
 * https://leetcode-cn.com/problems/surrounded-regions/solution/
 */
public class SurroundedRegions {
	public void solve(char[][] board) {
		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[i].length; j++) {
				if (i == 0 || j == 0 || i == board.length - 1 || j == board[i].length - 1) {
					if (board[i][j] == 'O')
						dfs(board,i, j);
				}
			}
		}

		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[i].length; j++) {
				if (board[i][j] == 'P')
					board[i][j] = 'O';
				else
					board[i][j] = 'X';
			}
		}
	}

	private void dfs(char[][] board, int i, int j) {
		if (i < 0 || j < 0 || i > board.length - 1 || j > board[i].length - 1)
			return;
		if (board[i][j] != 'O')
			return;
		board[i][j] = 'P';
		dfs(board, i-1, j);
		dfs(board, i+1, j);
		dfs(board, i, j-1);
		dfs(board, i, j+1);
	}

	public static void main(String[] args){
		SurroundedRegions main = new SurroundedRegions();
		char[][] board = new char[][]{
				"XXXX".toCharArray(),
				"XOOX".toCharArray(),
				"XXOX".toCharArray(),
				"XOXX".toCharArray(),
		};
		char[][] answer = new char[][]{
				"XXXX".toCharArray(),
				"XXXX".toCharArray(),
				"XXXX".toCharArray(),
				"XOXX".toCharArray(),
		};
		main.solve(board);
		System.out.println("-------board:-------");
		for(char[] arr: board)
			System.out.println(Arrays.toString(arr));
		System.out.println("-------answer:-------");
		for(char[] arr: answer)
			System.out.println(Arrays.toString(arr));
		char[][] boardII = new char[][]{
				"XOXOXO".toCharArray(),
				"OXOXOX".toCharArray(),
				"XOXOXO".toCharArray(),
				"OXOXOX".toCharArray(),
		};
		char[][] answerII = new char[][]{
				"XOXOXO".toCharArray(),
				"OXXXXX".toCharArray(),
				"XXXXXO".toCharArray(),
				"OXOXOX".toCharArray(),
		};
		main.solve(boardII);
		System.out.println("-------board:-------");
		for(char[] arr: boardII)
			System.out.println(Arrays.toString(arr));
		System.out.println("-------answer:-------");
		for(char[] arr: answerII)
			System.out.println(Arrays.toString(arr));
	}
}
