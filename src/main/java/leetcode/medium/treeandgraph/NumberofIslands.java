package leetcode.medium.treeandgraph;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/18.
 * 200. 岛屿数量
 * https://leetcode-cn.com/problems/number-of-islands/solution/
 */
public class NumberofIslands {

	public int numIslands(char[][] grid) {
		int num = 0;
		for (int i=0; i<grid.length; i++) {
			for (int j=0; j<grid[i].length; j++) {
				if (grid[i][j] == '0')
					continue;
				num++;
				dfs(grid, i, j);
			}
		}
		return num;
	}

	private void dfs (char[][] grid, int i, int j) {
		if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == '0')
			return;
		grid[i][j] = '0';
		dfs(grid, i+1, j);
		dfs(grid, i-1, j);
		dfs(grid, i, j+1);
		dfs(grid, i, j-1);
	}

	public static void main(String[] args){
		NumberofIslands main = new NumberofIslands();
		char[][] grid = new char[][]{
				new char[]{'1','1','1','1','0'},
				new char[]{'1','1','0','1','0'},
				new char[]{'1','1','0','0','0'},
				new char[]{'0','0','0','0','0'},
		};
		ResultCheck.check(main.numIslands(grid), 1);
		char[][] gridII = new char[][]{
				new char[]{'1','1','0','0','0'},
				new char[]{'1','1','0','0','0'},
				new char[]{'0','0','1','0','0'},
				new char[]{'0','0','0','1','1'},
		};
		ResultCheck.check(main.numIslands(gridII), 3);
	}
}
