package leetcode.hard.treeandgraph;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/9/3.
 * 547. 朋友圈
 * https://leetcode-cn.com/problems/friend-circles/solution/
 */
public class FriendCircles {
	public int findCircleNum1(int[][] M) {
		// iterate the left-bottom half part, when we get 1, then change the one in its row and col to 2,
		// and dfs the other 1s.
		int count = 0;
		for (int i=0; i < M.length; i++) {
			for (int j=0; j<=i; j++) {
				count += dfs1(M, i, j);
			}
		}
		for (int i=0; i < M.length; i++) {
			for (int j=0; j<=i; j++) {
				if(M[i][j] == 2)
					M[i][j] = 1;
			}
		}
		return count;
	}

	private int dfs1(int[][] M, int i, int j) {
		// change the 1 to 2, and dfs the row and col it belonged.
		if (i < 0 || j < 0 || i > M.length - 1 || j > M[i].length - 1)
			return 0;
		if (M[i][j] != 1)
			return 0;
		M[i][j] = 2;
		for (int p = j; p < M.length; p++) {
			dfs1(M, p, j);
		}
		for (int p = 0; p <= i; p++) {
			dfs1(M, i, p);
		}
		return 1;
	}

	public int findCircleNum(int[][] M) {
		int[] stus = new int[M.length];
		int count = 0;
		for (int i=0; i<M.length; i++) {
			if (stus[i] == 0) {
				dfs(M, stus, i);
				count++;
			}
		}
		return count;
	}
	private void dfs(int[][] M, int[] stus, int i) {
		stus[i] = 1;
		for (int j=0; j<M[i].length; j++) {
			if (M[i][j] == 1 && stus[j] == 0) {
				dfs(M, stus, j);
			}
		}
	}

	public static void main(String[] args){
		FriendCircles main = new FriendCircles();

		ResultCheck.check(main.findCircleNum(new int[][]{
				{1,1,0},
				{1,1,0},
				{0,0,1}
		}), 2);
		ResultCheck.check(main.findCircleNum(new int[][]{
				{1,1,0},
				{1,1,1},
				{0,1,1}
		}), 1);
		ResultCheck.check(main.findCircleNum(new int[][]{
				{1,0,1,0,1},
				{0,1,0,0,0},
				{1,0,1,0,1},
				{0,0,0,1,0},
				{1,0,1,0,1},
		}), 3);
	}
}
