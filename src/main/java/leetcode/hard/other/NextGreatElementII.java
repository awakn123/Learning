package leetcode.hard.other;

import leetcode.util.ResultCheck;

import java.util.Stack;

/**
 * Created by 曹云 on 2020/11/13.
 * 503. Next Greater Element II
 * https://leetcode-cn.com/problems/next-greater-element-ii/
 */
public class NextGreatElementII {
	public int[] nextGreaterElements(int[] nums) {
		int[] result = new int[nums.length];
		Stack<Integer> stack = new Stack<>();
		for (int i=nums.length -1; i>= -nums.length; i--) {
			int idx = i >=0 ? i : (i + nums.length);
			while (!stack.isEmpty() && nums[stack.peek()] <= nums[idx]) {
				stack.pop();
			}
			result[idx] = stack.isEmpty() ? -1 : nums[stack.peek()];
			stack.push(idx);
		}
		return result;
	}

	public static void main(String[] args){
		NextGreatElementII main = new NextGreatElementII();
		ResultCheck.check(main.nextGreaterElements(new int[]{1,2,1}), new int[]{2,-1,2});
	}

}
