package leetcode.medium.other;

import leetcode.util.ResultCheck;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 曹云 on 2020/8/29.
 * 169. 多数元素
 * https://leetcode-cn.com/problems/majority-element/solution/
 */
public class MajorityElement {
	public int majorityElement1(int[] nums) {
		Map<Integer, Integer> numCount = new HashMap<>(nums.length);
		int half = nums.length >> 1;
		for (int num: nums) {
			int count = numCount.getOrDefault(num, 0) + 1;
			if (count > half)
				return num;
			numCount.put(num, count);
		}
		return 0;
	}

	public int majorityElement2(int[] nums) {
		Arrays.sort(nums);
		return nums[nums.length >> 1];
	}

	public int majorityElement(int[] nums) {
		int candidate = 0, count = 0;
		for (int num: nums) {
			if (count == 0) {
				candidate = num;
				count++;
			} else if (candidate == num) {
				count++;
			} else {
				count--;
			}
		}
		return candidate;
	}

	public static void main(String[] args){
		MajorityElement main = new MajorityElement();
		ResultCheck.check(main.majorityElement(new int[]{3,2,3}), 3);
		ResultCheck.check(main.majorityElement(new int[]{2,2,1,1,1,2,2}), 2);
	}
}
