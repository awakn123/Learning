package leetcode.medium.arrayandstr;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/18.
 * 334. Increasing Triplet Subsequence
 * https://leetcode-cn.com/problems/increasing-triplet-subsequence/solution/
 */
public class IncreasingTripletSubseq {

	public boolean increasingTriplet(int[] nums) {
		return false;
	}

	public static void main(String[] args){
		IncreasingTripletSubseq main = new IncreasingTripletSubseq();
		ResultCheck.check(main.increasingTriplet(new int[]{1,2,3,4,5}), true);
		ResultCheck.check(main.increasingTriplet(new int[]{5,4,3,2,1}), false);
	}
}
