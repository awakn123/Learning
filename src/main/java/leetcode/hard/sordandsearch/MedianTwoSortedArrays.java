package leetcode.hard.sordandsearch;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/9/9.
 * 4. 寻找两个正序数组的中位数
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/
 */
public class MedianTwoSortedArrays {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length, n = nums2.length, mid = (m + n)/2, mi = 0, ni = 0;
		boolean isEven = (m + n)%2 == 0;
		if (!isEven)
			mid++;
		while(mid > 1 && mi < m && ni < n) {
			int l = mid/2;
			int mj = Math.min(mi + l - 1, m - 1);
			int nj = Math.min(ni + l - 1, n - 1);
			if (nums1[mj] <= nums2[nj]) {
				mid -= (mj + 1 - mi);
				mi = mj + 1;
			} else {
				mid -= (nj + 1 - ni);
				ni = nj + 1;
			}
		}

		if (mi == m) {
			ni += mid - 1;
		} else if (ni == n){
			mi += mid - 1;
		}
		if (isEven){
			int l,r;
			if (mi == m) {
				l = nums2[ni];
				r = nums2[ni + 1];
			} else if (ni == n) {
				l = nums1[mi];
				r = nums1[mi + 1];
			} else {
				if (nums1[mi] < nums2[ni]) {
					l = nums1[mi++];
				} else {
					l = nums2[ni++];
				}
				if (mi == m)
					r = nums2[ni];
				else if (ni == n)
					r = nums1[mi];
				else
					r = Math.min(nums1[mi], nums2[ni]);
			}
			return (double)(l+r)/2;
		} else {
			if (mi == m) {
				return nums2[ni];
			} else if (ni == n){
				return nums1[mi];
			}
			return Math.min(nums1[mi], nums2[ni]);
		}
	}

	public static void main(String[] args){
		MedianTwoSortedArrays main = new MedianTwoSortedArrays();
		ResultCheck.check(main.findMedianSortedArrays(new int[]{2}, new int[]{}), 2.0);
		ResultCheck.check(main.findMedianSortedArrays(new int[]{1,3}, new int[]{2}), 2.0);
		ResultCheck.check(main.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}), 2.5);
	}
}
