package leetcode.easy.array;

import java.util.Arrays;

/**
 * Created by 曹云 on 2020/8/5.
 * 48. Rotate Image
 * https://leetcode.com/problems/rotate-image/
 */
public class RotateImage {
	public void rotate(int[][] matrix) {
		if (matrix == null || matrix.length <= 1) return;
		int n = matrix.length;
		for (int i=0;i<n;i++){
			for (int j=i;j<n;j++) {
				swap(matrix, i, j, j, i);
			}
		}
		for (int i=0;i<n;i++){
			for (int j=0;j<(n>>1);j++) {
				swap(matrix, i, j, i, n-1-j);
			}
		}
	}
	public void swap(int[][] matrix, int i1, int j1, int i2, int j2){
		int tmp = matrix[i1][j1];
		matrix[i1][j1] = matrix[i2][j2];
		matrix[i2][j2] = tmp;
	}
	public void rotate2(int[][] matrix) {
		if (matrix == null || matrix.length <= 1) return;
		int n = matrix.length;
		for (int i=0;i<(n>>1);i++){
			for (int j = i; j<n-i-1; j++) {
				int p=i, q=j, cur = matrix[p][q];
				do {
					int np = q, nq = n-1-p;
					int tmp = matrix[np][nq];
					matrix[np][nq] = cur;
					cur = tmp;
					p = np;
					q = nq;
				} while(p!=i || q!=j);
			}
		}
	}

	public static void main(String[] args){
		int[][] matrixI = {
				{1,2,3},
				{4,5,6},
				{7,8,9},
		};
		int[][] answerI = {
				{7,4,1},
				{8,5,2},
				{9,6,3}
		};
		int[][] matrixII = {
				{5,1,9,11},
				{2,4,8,10},
				{13,3,6,7},
				{15,14,12,16},
		};
		int[][] answerII = {
				{15,13,2,5},
				{14,3,4,1},
				{12,6,8,9},
				{16,7,10,11}
		};
		RotateImage main = new RotateImage();
		main.rotate2(matrixI);
		checkResult(matrixI, answerI);
		main.rotate2(matrixII);
		checkResult(matrixII, answerII);
	}

	public static void checkResult(int[][] result, int[][] answer){
		if (result == null || answer == null) {
			System.out.println("Wrong, someone is null.");
			return;
		}
		for (int i=0;i<result.length;i++) {
			int[] row = result[i];
			for (int j =0;j<row.length;j++) {
				if (row[j] != answer[i][j]) {
					System.out.printf("Wrong, row: %d, col:%d\n", i,j);
					System.out.println(Arrays.toString(row));
					System.out.println(Arrays.toString(answer[i]));
					return;
				}
			}
		}
		System.out.println("pass");
	}
}
