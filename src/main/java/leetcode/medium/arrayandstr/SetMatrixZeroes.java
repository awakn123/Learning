package leetcode.medium.arrayandstr;

import leetcode.util.ResultCheck;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by 曹云 on 2020/8/18.
 * 73. Set Matrix Zeroes
 * https://leetcode-cn.com/problems/set-matrix-zeroes/solution/
 */
public class SetMatrixZeroes {
	public void setZeroes(int[][] matrix) {
		boolean firstCol = false;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					if (j == 0) {
						firstCol = true;
					} else {
						matrix[0][j] = 0;
					}
				}
			}
		}

		for (int i = matrix.length - 1; i >= 0; i--) {
			for (int j = matrix[i].length - 1; j >= 0; j--) {
				if (j == 0) {
					if (firstCol) {
						matrix[i][j] = 0;
					}
				} else if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
	}

	public static void main(String[] args) {
		SetMatrixZeroes main = new SetMatrixZeroes();
		int[][] matrixI = new int[][]{
				new int[]{1, 1, 1},
				new int[]{1, 0, 1},
				new int[]{1, 1, 1},
		};
		int[][] answerI = new int[][]{
				new int[]{1, 0, 1},
				new int[]{0, 0, 0},
				new int[]{1, 0, 1},
		};
		main.setZeroes(matrixI);
		ResultCheck.checkTwoDimension(matrixI, answerI);
		int[][] matrixII = new int[][]{
				new int[]{0, 1, 2, 0},
				new int[]{3, 4, 5, 2},
				new int[]{1, 3, 1, 5},
		};
		int[][] answerII = new int[][]{
				new int[]{0, 0, 0, 0},
				new int[]{0, 4, 5, 0},
				new int[]{0, 3, 1, 0},
		};
		main.setZeroes(matrixII);
		ResultCheck.checkTwoDimension(matrixII, answerII);
	}
}
