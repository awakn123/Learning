package leetcode.easy.sortsearch;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/12.
 * 88. Merge Sorted Array
 * https://leetcode.com/problems/merge-sorted-array/
 */
public class MergeSortedArray {
	public void merge(int[] nums1, int m, int[] nums2, int n) {

	}

	public static void main(String[] args){
		MergeSortedArray main = new MergeSortedArray();
		int[] nums1I = new int[]{1,2,3,0,0,0};
		int[] nums2I = new int[]{2,5,6};
		main.merge(nums1I, 3, nums2I, 3);
		int[] answerI = new int[]{1,2,2,3,5,6};
		ResultCheck.check(nums1I, answerI);
	}
}
