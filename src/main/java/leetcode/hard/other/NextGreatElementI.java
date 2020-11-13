package leetcode.hard.other;

import leetcode.util.ResultCheck;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by 曹云 on 2020/11/13.
 * https://leetcode-cn.com/problems/next-greater-element-i/
 * 496. 下一个更大元素 I
 * 单调栈
 */
public class NextGreatElementI {

	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		Map<Integer, Integer> map = new HashMap<>();
		Stack<Integer> stack = new Stack<>();
		for (int i = nums2.length - 1; i >= 0 ; i--) {
			while (!stack.isEmpty() && nums2[stack.peek()] <= nums2[i]) {
				stack.pop();
			}
			map.put(nums2[i], stack.isEmpty() ? -1 : nums2[stack.peek()]);
			stack.push(i);
		}
		int[] result = new int[nums1.length];
		for (int i = 0; i < nums1.length; i++) {
			result[i] = map.getOrDefault(nums1[i], - 1);
		}
		return result;
	}

	public static void main(String[] args){
		NextGreatElementI main = new NextGreatElementI();
		ResultCheck.check(main.nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2}), new int[]{-1,3,-1});
		ResultCheck.check(main.nextGreaterElement(new int[]{2,4}, new int[]{1,2,3,4}), new int[]{3,-1});
	}
}
