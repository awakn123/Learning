package leetcode.medium.sortandsearch;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/25.
 * 240. 搜索二维矩阵 II
 * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/solution/
 */
public class SearchA2dMatrixII {

	public boolean searchMatrix1(int[][] matrix, int target) {
		for (int i=0;i<matrix.length;i++) {
			int[] arr = matrix[i];
			int l = 0, r = arr.length;
			while (l < r) {
				if (arr[l] > target || arr[r - 1] < target)
					break;
				int mid = (r-l)/2 + l;
				if (arr[mid] == target)
					return true;
				else if (arr[mid] > target)
					r = mid;
				else
					l = mid + 1;
			}
		}
		return false;
	}

	public boolean searchMatrix2(int[][] matrix, int target) {
		if (matrix.length == 0) return false;
		return searchMatrix2(matrix, new int[]{0, 0}, new int[]{matrix.length, matrix[0].length}, target);
	}

	private boolean searchMatrix2(int[][] matrix, int[] pos, int[] len, int target) {
		if (len[0] == 0 || len[1] == 0) return false;
		int midCol = pos[1] + len[1]/2, top = pos[0], bottom = pos[0] + len[0];
		while(top < bottom) {
			int midRow = (top + bottom)/2;
			int mid = matrix[midRow][midCol];
			if (mid < target) {
				top = midRow + 1;
			} else if (mid > target) {
				bottom = midRow;
			} else {
				return true;
			}
		}
		//没有找到，递归查找左下、右上区域
		int[] LBpos = new int[]{top, pos[1]}, LBlen = new int[]{pos[0] + len[0] - top, midCol - pos[1]};
		int[] RTpos = new int[]{pos[0], midCol + 1}, RTlen = new int[]{top - pos[0], pos[1] + len[1] - midCol - 1};
		boolean resLB = searchMatrix2(matrix, LBpos, LBlen, target);
		boolean resRT = searchMatrix2(matrix, RTpos, RTlen, target);
		return resLB || resRT;
	}

	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix.length == 0) return false;
		int row = 0, col = matrix[0].length - 1;
		while (row < matrix.length && col >=0) {
			if (matrix[row][col] == target)
				return true;
			else if (matrix[row][col] > target)
				col--;
			else
				row++;
		}
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
