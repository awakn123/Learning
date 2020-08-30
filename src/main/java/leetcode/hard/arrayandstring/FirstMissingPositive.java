package leetcode.hard.arrayandstring;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/30.
 * 41. 缺失的第一个正数
 * https://leetcode-cn.com/problems/first-missing-positive/solution/
 */
public class FirstMissingPositive {

	public int firstMissingPositive(int[] nums) {
		return 0;
	}

	public static void main(String[] args){
		FirstMissingPositive main = new FirstMissingPositive();
		ResultCheck.check(main.firstMissingPositive(new int[]{1,2,0}), 3);
		ResultCheck.check(main.firstMissingPositive(new int[]{3,4,-1,1}), 2);
		ResultCheck.check(main.firstMissingPositive(new int[]{7,8,9,11,12}), 1);
	}
}
