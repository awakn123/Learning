package leetcode.medium.treeandgraph;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/18.
 * 200. 岛屿数量
 * https://leetcode-cn.com/problems/number-of-islands/solution/
 */
public class NumberofIsalnds {

	public int numIslands(char[][] grid) {
		return 0;
	}

	public static void main(String[] args){
		NumberofIsalnds main = new NumberofIsalnds();
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
