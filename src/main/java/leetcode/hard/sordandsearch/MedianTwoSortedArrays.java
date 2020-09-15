package leetcode.hard.sordandsearch;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/9/9.
 * 4. 寻找两个正序数组的中位数
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/
 */
public class MedianTwoSortedArrays {
	public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
		int m = nums1.length, n = nums2.length, midLen = ((m + n) >> 1) + 1,
			mi = 0, ni = 0;
		if (m == 0 && n == 0)
			return 0d;
		boolean isEven = (m + n)%2 == 0;
		while(midLen > 1 && mi != m && ni != n) {
			int hfLen = midLen >> 1;
			int mj = Math.min(mi + hfLen - 1, m - 1);
			int nj = Math.min(ni + hfLen - 1, n - 1);
			if (nums1[mj] <= nums2[nj]) {
				midLen -= (mj - mi + 1);
				mi = mj + 1;
			} else {
				midLen -= (nj - ni + 1);
				ni = nj + 1;
			}
		}

		int mid, midLeft = 0;
		if (mi == m) {
			ni += (midLen - 1);
			mid = nums2[ni--];
			mi--;
		} else if (ni == n) {
			mi += (midLen - 1);
			mid = nums1[mi--];
			ni--;
		} else {
			if (nums1[mi] < nums2[ni]) {
				mid = nums1[mi--];
				ni--;
			} else {
				mid = nums2[ni--];
				mi--;
			}
		}

		if (isEven) {
			if (mi == -1 && ni == -1) {
				return mid;
			} else if (mi == -1) {
				midLeft = nums2[ni];
			} else if (ni == -1) {
				midLeft = nums1[mi];
			} else {
				midLeft = Math.max(nums1[mi], nums2[ni]);
			}
			return (double) (mid + midLeft) / 2;
		} else {
			return mid;
		}

	}

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length, n = nums2.length;
		if (m > n)
			return findMedianSortedArrays(nums2, nums1);
		int l = 0, r = m;
		int midLeft = 0, midRight = 0;
		while (l <= r) {
			int mj = (l + r)/2, mi = mj - 1;
			int nj = (m + n + 1)/2 - mj, ni = nj - 1;
			int nums1_i = mi < 0 ? Integer.MIN_VALUE : nums1[mi];
			int nums1_j = mj >= m ? Integer.MAX_VALUE : nums1[mj];
			int nums2_i = ni < 0 ? Integer.MIN_VALUE : nums2[ni];
			int nums2_j = nj >= n ? Integer.MAX_VALUE : nums2[nj];

			if (nums1_i <= nums2_j) {
				midLeft = Math.max(nums1_i, nums2_i);
				midRight = Math.min(nums1_j, nums2_j);
				l = mj + 1;
			} else {
				r = mj - 1;
			}
		}
		if ((m + n)%2 == 1)
			return (double)midLeft;
		else
			return (double) (midLeft + midRight)/2;
	}
	public static void main(String[] args){
		MedianTwoSortedArrays main = new MedianTwoSortedArrays();
		ResultCheck.check(main.findMedianSortedArrays(new int[]{2}, new int[]{}), 2.0);
		ResultCheck.check(main.findMedianSortedArrays(new int[]{1,3}, new int[]{2}), 2.0);
		ResultCheck.check(main.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}), 2.5);
		ResultCheck.check(main.findMedianSortedArrays(new int[]{0,0,0,0,0}, new int[]{-1,0,0,0,0,0,1}), 0);
	}
}
