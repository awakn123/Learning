package leetcode.medium.arrayandstr;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/18.
 * 334. Increasing Triplet Subsequence
 * https://leetcode-cn.com/problems/increasing-triplet-subsequence/solution/
 */
public class IncreasingTripletSubseq {

	public boolean increasingTriplet1(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i+1; j < nums.length; j++) {
				if (nums[j] <= nums[i]) {
					continue;
				}
				for (int k = j+1; k < nums.length; k++) {
					if (nums[k] > nums[j])
						return true;
				}
			}
		}
		return false;
	}

	public boolean increasingTriplet2(int[] nums) {
		if (nums.length < 3) return false;
		int start = 0, mid = 1, nextstart = -1;
		for (; mid < nums.length - 1; mid++) {
			if (nums[mid] <= nums[start]) {
				start = mid;
			} else {
				for (int k = mid+1; k < nums.length; k++) {
					if (nums[k] > nums[mid])
						return true;
					else if (nums[k] > nums[start]){
						mid = k;
					} else if (nextstart == -1){
						nextstart = k;
					}
				}
				if (nextstart != -1) {
					start = nextstart;
					mid = start;
					nextstart = -1;
				}
			}
		}
		return false;
	}

	public boolean increasingTriplet(int[] nums) {
		if (nums.length < 3) return false;
		int min = Integer.MAX_VALUE;
		int second = Integer.MAX_VALUE;
		for (int num : nums) {
			if (num <= min) {
				min = num;
			} else if (num <= second) {
				second = num;
			} else {
				return true;
			}
		}
		return false;
	}


	public static void main(String[] args){
		IncreasingTripletSubseq main = new IncreasingTripletSubseq();
		ResultCheck.check(main.increasingTriplet(new int[]{10,20,1,}), true);
//		ResultCheck.check(main.increasingTriplet(new int[]{1,2,3,4,5}), true);
//		ResultCheck.check(main.increasingTriplet(new int[]{5,4,3,2,1}), false);
//		ResultCheck.check(main.increasingTriplet(new int[]{1,2,-10,-8,-7}), true);
	}
}
