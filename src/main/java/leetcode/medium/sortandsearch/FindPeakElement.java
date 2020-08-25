package leetcode.medium.sortandsearch;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/25.
 * 162. 寻找峰值
 * https://leetcode-cn.com/problems/find-peak-element/solution/
 */
public class FindPeakElement {

	public int findPeakElement(int[] nums) {
		return 0;
	}

	public static void main(String[] args){
		FindPeakElement main = new FindPeakElement();
		ResultCheck.check(main.findPeakElement(new int[]{1,2,3,1}), 2);
		ResultCheck.check(main.findPeakElement(new int[]{1,2,1,3,5,6,4}), 1);// or 5.
	}
}
