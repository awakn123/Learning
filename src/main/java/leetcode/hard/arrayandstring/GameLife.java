package leetcode.hard.arrayandstring;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/30.
 * 289. 生命游戏
 * https://leetcode-cn.com/problems/game-of-life/solution/
 */
public class GameLife {
	public void gameOfLife(int[][] board) {
		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[i].length; j++) {
				int num = countNum(board, i, j);
				if (board[i][j] == 1) {
					if (num < 2 || num > 3)
						board[i][j] = 3;// 二进制11，10位的1表示该值需要变化，个位的1记录原来的值。
				} else {
					if (num == 3)
						board[i][j] = 2;// 二进制11，10位的1表示该值需要变化，个位的0记录原来的值。
				}
			}
		}
		for (int i=0; i<board.length; i++)
			for (int j=0; j<board[i].length; j++)
				if (board[i][j] == 3)
					board[i][j] = 0;
				else if (board[i][j] == 2)
					board[i][j] = 1;
	}

	private int countNum(int[][] board, int col, int row) {
		int count = 0;
		for (int i = col - 1; i<=col+1; i++)
			for (int j = row - 1; j <= row +1; j++)
				if (i >=0 && j>=0 && i < board.length && j < board[i].length)
					if ((i!=col || j != row) && (board[i][j] & 1) == 1)
						count++;
		return count;
	}

	public static void main(String[] args){
		GameLife main = new GameLife();
		int[][] board = new int[][]{
				{0,1,0},
				{0,0,1},
				{1,1,1},
				{0,0,0}
		};
		main.gameOfLife(board);
		int[][] answer = new int[][]{
				{0,0,0},
				{1,0,1},
				{0,1,1},
				{0,1,0}
		};
		ResultCheck.checkTwoDimension(board, answer);
	}
}
