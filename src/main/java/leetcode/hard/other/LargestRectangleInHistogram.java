package leetcode.hard.other;

import leetcode.util.ResultCheck;

import java.util.Stack;

/**
 * Created by 曹云 on 2020/11/12.
 * 84. 柱状图中最大的矩形
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/
 * 单调栈
 */
public class LargestRectangleInHistogram {
	public int largestRectangleArea(int[] heights) {
		int[] left = new int[heights.length];
		int[] right = new int[heights.length];
		Stack<Integer> stack = new Stack<>();
		for (int i=0; i<heights.length; i++) {
			while(!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
				int k = stack.pop();
				right[k] = i - k;
			}
			left[i] = stack.isEmpty() ? (i + 1) : (i - stack.peek());
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int i = stack.pop();
			right[i] = heights.length - i;
		}
		int max = 0;
		for (int i=0; i<heights.length; i++) {
			max = Math.max(heights[i] * (left[i] + right[i] - 1), max);
		}
		return max;
	}

	public static void main(String[] args){
		LargestRectangleInHistogram main = new LargestRectangleInHistogram();
		ResultCheck.check(main.largestRectangleArea(new int[]{2,1,5,6,2,3}), 10);
	}
}
