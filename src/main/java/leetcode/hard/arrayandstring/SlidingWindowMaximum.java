package leetcode.hard.arrayandstring;

import leetcode.util.ResultCheck;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by 曹云 on 2020/8/30.
 * 239. 滑动窗口最大值
 * https://leetcode-cn.com/problems/sliding-window-maximum/solution/
 */
public class SlidingWindowMaximum {
	public int[] maxSlidingWindow1(int[] nums, int k) {
		int[] result = new int[nums.length - k + 1];
		Deque<Integer> deque = new ArrayDeque<>();
		for (int i=0; i<k; i++) {
			clearDeque(deque, i, k, nums);
			deque.addLast(i);
		}
		result[0] = nums[deque.getFirst()];
		for (int i=k; i<nums.length; i++) {
			clearDeque(deque,i,k,nums);
			deque.addLast(i);
			result[i-k+1] = nums[deque.getFirst()];
		}
		return result;
	}
	private void clearDeque(Deque<Integer> deque, int i, int k, int[] nums) {
		if (deque.size() > 0 && deque.getFirst() == i-k) {
			deque.removeFirst();
		}
		while(deque.size() > 0 && nums[deque.getLast()] < nums[i])
			deque.removeLast();
	}

	public int[] maxSlidingWindow(int[] nums, int k) {
		int[] result = new int[nums.length - k + 1];
		int[] left = new int[nums.length];
		int[] right = new int[nums.length];
		int max = Integer.MIN_VALUE;
		for (int i=0; i<nums.length; i++) {
			if (i%k == 0)
				max = Integer.MIN_VALUE;
			max = Math.max(max, nums[i]);
			left[i] = max;
		}
		for (int i=nums.length - 1; i>=0; i--) {
			if (i%k == 0)
				max = Integer.MIN_VALUE;
			max = Math.max(max, nums[i]);
			right[i] = max;
		}
		for (int i=0;i<nums.length - k + 1; i++) {
			result[i] = Math.max(right[i], left[i+k-1]);
		}
		return result;
	}
	public static void main(String[] args){
		SlidingWindowMaximum main = new SlidingWindowMaximum();
		ResultCheck.check(main.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3), new int[]{3,3,5,5,6,7});
		ResultCheck.check(main.maxSlidingWindow(new int[]{1,3,1,2,0,5}, 3), new int[]{3,3,2,5});
	}
}
