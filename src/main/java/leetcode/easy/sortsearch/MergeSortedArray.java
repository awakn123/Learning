package leetcode.easy.sortsearch;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/12.
 * 88. Merge Sorted Array
 * https://leetcode.com/problems/merge-sorted-array/
 */
public class MergeSortedArray {
	public void merge2(int[] nums1, int m, int[] nums2, int n) {
		int j = 0;
		for (int i = 0; i < m + n; i++) {
			if (j >= n) {
				break;
			}
			if (i == m + j) {
				nums1[i] = nums2[j];
				j++;
			} else if (nums1[i] > nums2[j]) {
				int tmp = nums1[i];
				nums1[i] = nums2[j];
				int k = i + 1;
				while (k <= m + j) {
					int nextTmp = nums1[k];
					nums1[k] = tmp;
					tmp = nextTmp;
					k++;
				}
				j++;
			}
		}
	}

	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int i = m - 1, j = n - 1;
		while (i >= 0 || j >= 0) {
			if (j < 0 || (i >= 0 && nums1[i] >= nums2[j])) {
				nums1[i + j + 1] = nums1[i];
				i--;
			} else if (i < 0 || nums1[i] < nums2[j]) {
				nums1[i + j + 1] = nums2[j];
				j--;
			}
		}
	}


	public static void main(String[] args) {
		MergeSortedArray main = new MergeSortedArray();
		int[] nums1I = new int[]{1, 2, 3, 0, 0, 0};
		int[] nums2I = new int[]{2, 5, 6};
		main.merge(nums1I, 3, nums2I, 3);
		int[] answerI = new int[]{1, 2, 2, 3, 5, 6};
		ResultCheck.check(nums1I, answerI);
		int[] nums1II = new int[]{4, 5, 6, 0, 0, 0};
		int[] nums2II = new int[]{1, 2, 3};
		main.merge(nums1II, 3, nums2II, 3);
		int[] answerII = new int[]{1, 2, 3, 4, 5, 6};
		ResultCheck.check(nums1II, answerII);
	}
}
