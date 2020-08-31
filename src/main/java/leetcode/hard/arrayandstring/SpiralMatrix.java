package leetcode.hard.arrayandstring;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 曹云 on 2020/8/30.
 * 54. 螺旋矩阵
 * https://leetcode-cn.com/problems/spiral-matrix/solution/
 */
public class SpiralMatrix {

	public List<Integer> spiralOrder(int[][] matrix) {
		if (matrix.length == 0) return new ArrayList<>();
		int m = matrix.length, n = matrix[0].length;
		List<Integer> list = new ArrayList<>(m * n);
		int colT = 0, colB = m - 1;
		int rowL = 0, rowR = n - 1;
		while(rowL < rowR && colT < colB) {
			for (int i = rowL; i < rowR; i++)
				list.add(matrix[colT][i]);
			for (int i = colT; i < colB; i++)
				list.add(matrix[i][rowR]);
			for (int i = rowR; i > rowL; i--)
				list.add(matrix[colB][i]);
			for (int i = colB; i > colT; i--)
				list.add(matrix[i][rowL]);
			rowL++; rowR--; colT++; colB--;
		}
		if (rowL == rowR && colT == colB) {
			list.add(matrix[colT][rowL]);
		} else if (rowL == rowR) {
			for (int i = colT; i <= colB; i++)
				list.add(matrix[i][rowR]);
		} else if (colT == colB) {
			for (int i = rowL; i <= rowR; i++)
				list.add(matrix[colT][i]);
		}

		return list;
	}
	public static void main(String[] args){
		SpiralMatrix main = new SpiralMatrix();
		int[][] matrixI = new int[][]{
				{1,2,3},
				{4,5,6},
				{7,8,9}
		};
		List<Integer> answerI = Lists.newArrayList(1,2,3,6,9,8,7,4,5);
		ResultCheck.checkList(main.spiralOrder(matrixI), answerI);
		int[][] matrixII = new int[][]{
				{1,2,3,4},
				{5,6,7,8},
				{9,10,11,12}
		};
		List<Integer> answerII = Lists.newArrayList(1,2,3,4,8,12,11,10,9,5,6,7);
		ResultCheck.checkList(main.spiralOrder(matrixII), answerII);
		int[][] matrixIII = new int[][]{
				{6,9,7},
		};
		List<Integer> answerIII = Lists.newArrayList(6,9,7);
		ResultCheck.checkList(main.spiralOrder(matrixIII), answerIII);
	}
}
