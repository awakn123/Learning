package leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSums {
	public static void main(String[] args) {
	    int[] result = twoSum(new int[]{1,2,3,4}, 5);
		System.out.println("should:0,3,end:" + result[0]+","+result[1]);
		result = twoSum(new int[]{2,6,4,3}, 10);
		System.out.println("should:1,2,end:" + result[0]+","+result[1]);
		result = twoSum(new int[]{4,7,22,10}, 32);
		System.out.println("should:2,3,end:" + result[0]+","+result[1]);
	}

	public static int[] twoSumOriginal(int[] nums, int target){
		for (int i =0;i< nums.length;i++) {
			int num1 = nums[i];
			for (int j=i + 1; j < nums.length; j++) {
				int num2 = nums[j];
				if (num1 + num2 == target)
					return new int[]{i,j};
			}
		}
		return null;
	}
	public static int[] twoSum2(int[] nums, int target){
		if (nums == null || nums.length < 2) return null;
		Map<Integer, Integer> map = new HashMap();
		for (int i =0;i<nums.length;i++) {
			if (map.containsKey(target - nums[i]))
				return new int[]{map.get(target - nums[i]), i};
			map.put(nums[i], i);
		}
		return null;
	}
	public static int[] twoSum(int[] nums, int target){
		int[] sum = new int[2];
		for (int i =0;i< nums.length;i++) {
			for (int j=i + 1; j < nums.length; j++) {
				if (target - nums[i] == nums[j]) {
					sum[0] = i;
					sum[1] = j;
					return sum;
				}
			}
		}
		return null;
	}
}
