package leetcode.hard.arrayandstring;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/30.
 * 289. 生命游戏
 * https://leetcode-cn.com/problems/game-of-life/solution/
 */
public class GameLife {
	public void gameOfLife(int[][] board) {

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
