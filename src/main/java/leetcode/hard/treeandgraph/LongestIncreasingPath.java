package leetcode.hard.treeandgraph;

import leetcode.util.ResultCheck;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by 曹云 on 2020/9/3.
 * 329. 矩阵中的最长递增路径
 * https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/solution/
 */
public class LongestIncreasingPath {
	public int longestIncreasingPath1(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) return 0;
		int rowL = matrix.length, colL = matrix[0].length;
		int[][] pathNum = new int[rowL][colL];
		int ans = 0;

		for (int i=0; i<rowL; i++) {
			for (int j=0; j<colL; j++) {
				ans = Math.max(ans, dfs(matrix, pathNum, i,j));
			}
		}

		return ans;
	}

	private int dfs(int[][] matrix, int[][] pathNum, int i, int j) {
		if (i < 0 || j < 0 || i > matrix.length - 1 || j > matrix[0].length - 1)
			return 0;
		if (pathNum[i][j] != 0)
			return pathNum[i][j];
		pathNum[i][j] = 1;
		for (int[] dir: dirs) {
			int nexti = i + dir[0];
			int nextj = j + dir[1];
			if (nexti<0 || nextj<0 || nexti > matrix.length - 1 || nextj > matrix[0].length - 1)
				continue;
			if (matrix[nexti][nextj] < matrix[i][j]) {
				pathNum[i][j] = Math.max(pathNum[i][j], dfs(matrix, pathNum, nexti, nextj) + 1);
			}
		}

		return pathNum[i][j];
	}
	private int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

	public int longestIncreasingPath(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) return 0;
		int rowL = matrix.length, colL = matrix[0].length;
		int[][] outdegs = new int[rowL][colL];
		for (int i=0; i<rowL; i++) {
			for (int j=0; j<colL; j++) {
				for (int[] dir: dirs) {
					int nexti = i + dir[0], nextj = j + dir[1];
					if (nexti < 0 || nextj < 0 || nexti > rowL - 1 || nextj > colL - 1)
						continue;
					if (matrix[nexti][nextj] > matrix[i][j])
						outdegs[i][j]++;
				}
			}
		}

		Queue<int[]> queue = new LinkedList<>();
		for (int i=0; i<rowL; i++) {
			for (int j = 0; j < colL; j++) {
				if (outdegs[i][j] == 0)
					queue.offer(new int[]{i, j});
			}
		}

		int ans = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size > 0) {
				int[] pos = queue.poll();
				for (int[] dir: dirs) {
					int nexti = pos[0] + dir[0], nextj = pos[1] + dir[1];
					if (nexti < 0 || nextj < 0 || nexti > rowL - 1 || nextj > colL - 1)
						continue;
					if (matrix[nexti][nextj] < matrix[pos[0]][pos[1]]) {
						outdegs[nexti][nextj]--;
						if (outdegs[nexti][nextj] == 0)
							queue.offer(new int[]{nexti, nextj});
					}
				}
				size--;
			}
			ans++;
		}
		return ans;
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
		ResultCheck.check(main.longestIncreasingPath(new int[][]{
				{0},{1},{5},{5}
		}), 3);
	}
}
