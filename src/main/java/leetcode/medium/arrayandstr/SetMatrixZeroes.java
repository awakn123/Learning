package leetcode.medium.arrayandstr;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/18.
 * 73. Set Matrix Zeroes
 * https://leetcode-cn.com/problems/set-matrix-zeroes/solution/
 */
public class SetMatrixZeroes {
	public void setZeroes(int[][] matrix) {

	}

	public static void main(String[] args){
		SetMatrixZeroes main = new SetMatrixZeroes();
		int[][] matrixI = new int[][]{
				new int[]{1,1,1},
				new int[]{1,0,1},
				new int[]{1,1,1},
		};
		int[][] answerI = new int[][]{
				new int[]{1,0,1},
				new int[]{0,0,0},
				new int[]{1,0,1},
		};
		main.setZeroes(matrixI);
		ResultCheck.checkTwoDimension(matrixI, answerI);
		int[][] matrixII = new int[][]{
				new int[]{0,1,2,0},
				new int[]{3,4,5,2},
				new int[]{1,3,1,5},
		};
		int[][] answerII = new int[][]{
				new int[]{0,0,0,0},
				new int[]{0,4,5,0},
				new int[]{0,3,1,0},
		};
		main.setZeroes(matrixII);
		ResultCheck.checkTwoDimension(matrixII, answerII);
	}
}
