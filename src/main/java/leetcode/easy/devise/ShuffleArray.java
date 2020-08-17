package leetcode.easy.devise;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by 曹云 on 2020/8/16.
 * 384. Shuffle an Array
 * https://leetcode.com/problems/shuffle-an-array/
 */
public class ShuffleArray {
	int[] nums;

	public ShuffleArray(int[] nums) {
		this.nums = nums;
	}

	/** Resets the array to its original configuration and return it. */
	public int[] reset() {
		return nums;
	}

	/** Returns a random shuffling of the array. */
	public int[] shuffle() {
		int[] result = Arrays.copyOf(nums, nums.length);
		Random r = new Random();
		for (int i=result.length - 1; i>=0; i--) {
			int idx = (int)Math.floor(Math.random() * (i + 1));
			int tmp = result[i];
			result[i] = result[idx];
			result[idx] = tmp;
		}
		return result;
	}

	public static void main(String[] args){
		// 以数字集合 1, 2 和 3 初始化数组。
		int[] nums = {1,2,3};
		ShuffleArray solution = new ShuffleArray(nums);

		// 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
		solution.shuffle();

		// 重设数组到它的初始状态[1,2,3]。
		System.out.println(Arrays.toString(solution.reset()));

		// 随机返回数组[1,2,3]打乱后的结果。
		System.out.println(Arrays.toString(solution.shuffle()));

	}
}
