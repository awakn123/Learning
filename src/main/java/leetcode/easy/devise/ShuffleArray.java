package leetcode.easy.devise;

import java.util.Arrays;

/**
 * Created by 曹云 on 2020/8/16.
 * 384. Shuffle an Array
 * https://leetcode.com/problems/shuffle-an-array/
 */
public class ShuffleArray {

	public ShuffleArray(int[] nums) {

	}

	/** Resets the array to its original configuration and return it. */
	public int[] reset() {
		return null;
	}

	/** Returns a random shuffling of the array. */
	public int[] shuffle() {
		return null;
	}

	public static void main(String[] args){
		// 以数字集合 1, 2 和 3 初始化数组。
		int[] nums = {1,2,3};
		ShuffleArray solution = new ShuffleArray(nums);

		// 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
		solution.shuffle();

		// 重设数组到它的初始状态[1,2,3]。
		solution.reset();

		// 随机返回数组[1,2,3]打乱后的结果。
		System.out.println(Arrays.toString(solution.shuffle()));

	}
}
