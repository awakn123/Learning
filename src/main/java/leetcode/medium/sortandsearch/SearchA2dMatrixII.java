package leetcode.medium.sortandsearch;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/25.
 * 240. 搜索二维矩阵 II
 * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/solution/
 */
public class SearchA2dMatrixII {

	public boolean searchMatrix(int[][] matrix, int target) {
		return false;
	}

	public static void main(String[] args){
		SearchA2dMatrixII main = new SearchA2dMatrixII();
		int[][] matrix = new int[][]{
				new int[]{1,4,7,11,15},
				new int[]{2,5,8,12,19},
				new int[]{3,6,9,16,22},
				new int[]{10,13,14,17,24},
				new int[]{18,21,23,26,30},
		};
		ResultCheck.check(main.searchMatrix(matrix, 5),  true);
		ResultCheck.check(main.searchMatrix(matrix, 20),  false);
	}
}
