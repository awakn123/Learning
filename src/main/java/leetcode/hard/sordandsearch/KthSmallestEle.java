package leetcode.hard.sordandsearch;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/9/9.
 * 378. 有序矩阵中第K小的元素
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/solution/
 */
public class KthSmallestEle {
	public int kthSmallest(int[][] matrix, int k) {
		return 0;
	}

	public static void main(String[] args){
		KthSmallestEle main = new KthSmallestEle();
		int[][] matrix = new int[][]{
				{1,5,9},
				{10,11,13},
				{12,13,15},
		};
		ResultCheck.check(main.kthSmallest(matrix, 8), 13);
	}
}
