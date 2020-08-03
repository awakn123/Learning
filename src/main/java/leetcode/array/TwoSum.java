package leetcode.array;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 曹云 on 2020/8/3.
 * 1. Two Sum
 * https://leetcode.com/problems/two-sum/
 */
public class TwoSum {
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		int[] result = new int[2];
		for (int i=0; i<nums.length; i++) {
			if (map.containsKey(nums[i])) {
				result[0] = map.get(nums[i]);
				result[1] = i;
				break;
			} else {
				map.put(target - nums[i], i);
			}
		}
		return result;
	}
	public static void main(String[] args){
		TwoSum main = new TwoSum();
		int[] numsI = {4,6,8,3};
		int targetI = 12;
		int[] answerI = {0,2};
		int[] resultI = main.twoSum(numsI, targetI);
		checkResult(resultI, answerI);
		int[] numsII = {7,0,9,2};
		int targetII = 11;
		int[] answerII = {2,3};
		int[] resultII = main.twoSum(numsII, targetII);
		checkResult(resultII, answerII);
		int[] numsIII = {5,7,9,10};
		int targetIII = 15;
		int[] answerIII = {0,3};
		int[] resultIII = main.twoSum(numsIII, targetIII);
		checkResult(resultIII, answerIII);
	}

	public static void checkResult(int[] result, int[] answer){
		if (result.length != answer.length) {
			System.out.printf("Wrong, result.length: %d, answer.length: %d.\n", result.length, answer.length);
			return;
		}
		if (Arrays.equals(result, answer)) {
			System.out.println("Right");
		} else {
			System.out.println("Wrong");
			System.out.printf("result:%s\n", Arrays.toString(result));
			System.out.printf("answer:%s\n", Arrays.toString(answer));
		}
	}
}
