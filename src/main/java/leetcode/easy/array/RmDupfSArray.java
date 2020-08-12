package leetcode.easy.array;

/**
 * Created by 曹云 on 2020/8/1.
 * 26.Remove Duplicates from Sorted Array
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 */
public class RmDupfSArray {

	public int removeDuplicates(int[] nums) {
		if (nums.length == 0 || nums.length == 1) return nums.length;
		Integer tmp = null;
		int j =0;
		for (int i =0;i<nums.length;i++){
			nums[j] = nums[i];
			if (tmp == null || nums[i] != tmp){
				tmp = nums[i];
				j++;
			}
		}
		return j;
	}

	public int removeDuplicates2(int[] nums) {
		if (nums.length == 0 || nums.length == 1) return nums.length;
		int j =0;
		for (int i =1;i<nums.length;i++){
			if (nums[i] != nums[j]) {
				j++;
				nums[j] = nums[i];
			}
		}
		return j + 1;
	}
	public static void main(String[] args){
		RmDupfSArray m = new RmDupfSArray();
		int[] testArray1 = {1,1,2,3,3};
		int[] testArray1Result = {1,2,3};
		int len = m.removeDuplicates2(testArray1);
		checkSuccess(testArray1, testArray1Result, len);
	}

	public static void checkSuccess(int[] array, int[] result, int len) {
		boolean pass = true;
		System.out.println("len:"+len +", result.len:"+result.length);
		if (len == result.length) {
			for (int i =0;i<len;i++){
				System.out.println(result[i] + ":" + array[i]);
				if (result[i] != array[i]){
					pass = false;
					break;
				}
			}
		} else {
			pass = false;
		}
		if (pass) {
			System.out.println("testArray1 success.");
		} else {
			System.out.println("testArray1 fail");
		}
	}
}
