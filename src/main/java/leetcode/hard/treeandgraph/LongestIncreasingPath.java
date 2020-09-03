package leetcode.hard.treeandgraph;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/9/3.
 * 329. 矩阵中的最长递增路径
 * https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/solution/
 */
public class LongestIncreasingPath {
	public int longestIncreasingPath(int[][] matrix) {
		return 0;
	}

	public static void main(String[] args){
		LongestIncreasingPath main = new LongestIncreasingPath();
		ResultCheck.check(main.longestIncreasingPath(new int[][]{
				{9,9,4},
				{6,6,8},
				{2,1,1}
		}), 4);
		ResultCheck.check(main.longestIncreasingPath(new int[][]{
				{3,4,5},
				{3,2,6},
				{2,2,1}
		}), 4);
	}
}
