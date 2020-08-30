package leetcode.hard.arrayandstring;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.List;

/**
 * Created by 曹云 on 2020/8/30.
 * 54. 螺旋矩阵
 * https://leetcode-cn.com/problems/spiral-matrix/solution/
 */
public class SpiralMatrix {

	public List<Integer> spiralOrder(int[][] matrix) {

		return null;
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
	}
}
