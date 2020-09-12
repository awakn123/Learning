package leetcode.hard.sordandsearch;

import leetcode.util.ResultCheck;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by 曹云 on 2020/9/9.
 * 378. 有序矩阵中第K小的元素
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/solution/
 */
public class KthSmallestEle {
	public int kthSmallest(int[][] matrix, int k) {
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		int n = matrix.length;
		for (int i = 0; i < n; i++) {
			pq.offer(new int[]{matrix[i][0], i, 0});
		}

		for (int i = 0; i < k - 1; i++) {
			int[] cur = pq.poll();
			if (cur[2] != n - 1) {
				pq.offer(new int[]{matrix[cur[1]][cur[2] + 1], cur[1], cur[2] + 1});
			}
		}
		return pq.poll()[0];
	}
	public int kthSmallest1(int[][] matrix, int k) {
		int n = matrix.length;
		int[] res = new int[n * n], tmp = new int[n * n];
		merge(matrix, res, tmp, 0, n - 1);
//		System.out.println(Arrays.toString(res));
		return res[k - 1];
	}

	private void merge(int[][] matrix, int[] res, int[] tmp, int left ,int right) {
		int n = matrix.length;
		if (left == right) {
			for (int i = 0; i < n; i++) {
				res[left * n + i] = matrix[left][i];
			}
			return;
		}
		int mid = (right + left)/2;
		merge(matrix, res, tmp, left, mid);
		merge(matrix, res, tmp, mid + 1, right);
		int lStart = left * n, rStart = (mid + 1) * n, lLen = (mid - left + 1) * n, rLen = (right - mid) * n;
		int l = lStart, r = rStart, k = lStart;
		while(l - lStart < lLen && r - rStart < rLen) {
			if (res[l] < res[r]) {
				tmp[k++] = res[l++];
			} else {
				tmp[k++] = res[r++];
			}
		}
		while(l - lStart < lLen) {
			tmp[k++] = res[l++];
		}
		while(r - rStart < rLen) {
			tmp[k++] = res[r++];
		}
		for (int i = lStart; i<lStart + lLen + rLen;i++) {
			res[i] = tmp[i];
		}
	}

	public static void main(String[] args){
		KthSmallestEle main = new KthSmallestEle();
		int[][] matrix = new int[][]{
				{1,5,9},
				{10,11,13},
				{12,13,15},
		};
		ResultCheck.check(main.kthSmallest(matrix, 8), 13);
		int[][] matrixII = new int[][]{
				{1,2},
				{1,3},
		};
		ResultCheck.check(main.kthSmallest(matrixII, 2), 1);
		int[][] matrixIII = new int[][]{
				{1,3,5},
				{6,7,12},
				{11,14,14},
		};
		ResultCheck.check(main.kthSmallest(matrixIII, 3), 5);
		int[][] matrixIV = new int[][]{
				{1,4,7,11,15},
				{2,5,8,12,19},
				{3,6,9,16,22},
				{10,13,14,17,24},
				{18,21,23,26,30}
		};
		ResultCheck.check(main.kthSmallest(matrixIV, 20), 21);
	}
}
