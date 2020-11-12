package leetcode.hard.other;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/11/12.
 * 84. 柱状图中最大的矩形
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/
 */
public class LargestRectangleInHistogram {
	public int largestRectangleArea(int[] heights) {
		for (int i=0; i<heights.length; i++) {

		}
		return 0;
	}

	public static void main(String[] args){
		LargestRectangleInHistogram main = new LargestRectangleInHistogram();
		ResultCheck.check(main.largestRectangleArea(new int[]{2,1,5,6,2,3}), 10);
	}
}
