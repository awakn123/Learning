package leetcode.array;

import java.util.Arrays;

/**
 * Created by 曹云 on 2020/8/3.
 * 283. Move Zeroes
 * https://leetcode.com/problems/move-zeroes/
 */
public class MoveZeroes {
	/**
	 * merge sort
	 * it doesn't need this complex function.
	 * @param nums
	 */
	public void moveZeroes(int[] nums) {
		if (nums == null || nums.length <=1) return;
		int[] result = mergeSort(nums);
		for (int i=0; i<nums.length; i++){
			result[i] = nums[i];
		}
	}

	public int[] mergeSort(int[] nums) {
		if (nums.length <= 1) return nums;
		if (nums.length == 2) {
			if (nums[0] == 0 && nums[1] != 0)
				swap(nums, 0, 1);
			return nums;
		}
		int idx = (nums.length >> 1) + 1;
		int[] nums1 = mergeSort(Arrays.copyOfRange(nums, 0, idx));
		int[] nums2 = mergeSort(Arrays.copyOfRange(nums, idx, nums.length));
		int i=0,j=0;
		while(i<nums1.length && j<nums2.length) {
			if (nums1[i] == 0) {
				nums[i+j] = nums2[j];
				j++;
			} else {
				nums[i+j] = nums1[i];
				i++;
			}
		}
		while(i<nums1.length)
			nums[i+j] = nums1[i++];
		while(j<nums2.length)
			nums[i+j] = nums2[j++];
		return nums;
	}
	public void swap(int[] nums, int idx1, int idx2){
		int tmp = nums[idx1];
		nums[idx1] = nums[idx2];
		nums[idx2] = tmp;
	}

	/**
	 * O(n) and no extra space!
	 * @param nums
	 */
	public void moveZeroes2(int[] nums) {
		if (nums == null || nums.length <=1) return;
		int zeroIdx = -1;
		for (int i=0; i<nums.length; i++) {
			if (nums[i] == 0 && zeroIdx == -1) {
				zeroIdx = i;
			} else if (nums[i] != 0 && zeroIdx != -1){
				swap(nums, zeroIdx, i);
				zeroIdx = zeroIdx + 1;
			}
		}
	}

	public static void main(String[] args){
		MoveZeroes main = new MoveZeroes();
		int[] numsI = {1,2,3,4,5};
		int[] answerI = {1,2,3,4,5};
		int[] numsII = {1,0,5,0,9};
		int[] answerII = {1,5,9,0,0};
		int[] numsIII = {0,0,4,0,0};
		int[] answerIII = {4,0,0,0,0};
		main.moveZeroes2(numsI);
		main.moveZeroes2(numsII);
		main.moveZeroes2(numsIII);
		checkResult(numsI, answerI);
		checkResult(numsII, answerII);
		checkResult(numsIII, answerIII);
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
