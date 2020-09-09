package leetcode.hard.sordandsearch;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/9/9.
 * 4. 寻找两个正序数组的中位数
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/
 */
public class MedianTwoSortedArrays {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		return 0d;
	}
	public static void main(String[] args){
		MedianTwoSortedArrays main = new MedianTwoSortedArrays();
		ResultCheck.check(main.findMedianSortedArrays(new int[]{1,3}, new int[]{2}), 2.0);
		ResultCheck.check(main.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}), 2.5);
	}
}
