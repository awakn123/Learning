package leetcode.hard.arrayandstring;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/30.
 * 239. 滑动窗口最大值
 * https://leetcode-cn.com/problems/sliding-window-maximum/solution/
 */
public class SlidingWindowMaximum {
	public int[] maxSlidingWindow(int[] nums, int k) {
		return nums;
	}
	public static void main(String[] args){
		SlidingWindowMaximum main = new SlidingWindowMaximum();
		ResultCheck.check(main.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3), new int[]{3,3,5,5,6,7});
	}
}
