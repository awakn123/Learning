package leetcode.array;

import java.util.Arrays;

/**
 * Created by 曹云 on 2020/8/1.
 * 189.Rotate Array
 * https://leetcode.com/problems/rotate-array/
 */
public class RotateArray {
	/**
	 * O(n * k)
	 * @param nums
	 * @param k
	 */
	public void rotate(int[] nums, int k) {
		if (nums.length <= 1 || k <=0 ) return;
		if (k > nums.length) {
			k = k%nums.length;
		}
		for (int i=0;i<k;i++) {
			for (int j = nums.length - 2; j>=0;j--) {
				int tmp = nums[j];
				nums[j] = nums[j+1];
				nums[j+1] = tmp;
			}
		}
	}

	/**
	 * O(n)
	 * @param nums
	 * @param k
	 */
	public void rotate2(int[] nums, int k) {
		if (nums.length <= 1 || k <=0 ) return;
		if (k > nums.length) {
			k = k%nums.length;
		}
		if (k == 0) return;
//		int[] initialNums = Arrays.copyOf(nums, nums.length);
		int[] initialNums = new int[nums.length];
		System.arraycopy(nums, 0, initialNums, 0,
				nums.length);
		for (int i=0; i<nums.length; i++) {
			int target = (i+k)%nums.length;
			nums[target] = initialNums[i];
		}
	}
	public void rotate3(int[] nums, int k) {
		if (nums.length <= 1 || k <=0 ) return;
		if (k > nums.length) {
			k = k%nums.length;
		}
		if (k == 0) return;
		int ini = 0, cur, curV, tarV, s = 0;
		while(true) {
			cur = ini;
			curV = nums[cur];
			do{
				int tar = (cur+k)%nums.length;
				tarV = nums[tar];
				nums[tar] = curV;
				cur = tar;
				curV = tarV;
				s++;
				if (s == nums.length) break;
			} while(cur!=ini);
			if (s == nums.length) break;
			ini++;
		}
	}

	/**
	 * Unexpected.
	 * reverse.
	 * @param nums
	 * @param k
	 */
	public void rotate4(int[] nums, int k) {
		k %= nums.length;
		reverse(nums, 0, nums.length - 1);
		reverse(nums, 0, k - 1);
		reverse(nums, k, nums.length - 1);
	}
	public void reverse(int[] nums, int start, int end) {
		while (start < end) {
			int temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
			start++;
			end--;
		}
	}

	public static void main(String[] args){
		RotateArray main = new RotateArray();
		int[] arrayI = {1,2,3,4,5};
		int kI = 3;
		int[] answerI = {3,4,5,1,2};
		main.rotate3(arrayI, kI);
		checkResult(arrayI, answerI);
		int[] arrayII = {3,4,5,6,7,8,9};
		int kII = 10;
		int[] answerII = {7,8,9, 3,4,5,6};
		main.rotate3(arrayII, kII);
		checkResult(arrayII, answerII);
		int[] arrayIII = {1,2,3,4,5,6,7};
		int kIII = 5;
		int[] answerIII = {3,4,5,6,7,1,2};
		main.rotate3(arrayIII, kIII);
		checkResult(arrayIII, answerIII);
	}

	public static void checkResult(int[] result, int[] answer) {
		if (result.length != answer.length) {
			System.out.println("Wrong Length:"+result.length + ", Answer:" + answer.length);
			return;
		}
		boolean pass = true;
		for (int i=0;i<result.length;i++){
			if (result[i] != answer[i]) {
				System.out.println("Wrong " + i + ":" + result[i] + ", answer:" + answer[i]);
				pass = false;
				break;
			}
		}
		System.out.println(pass ? "pass" : "wrong");
	}
}
