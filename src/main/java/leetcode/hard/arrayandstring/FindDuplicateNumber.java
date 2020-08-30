package leetcode.hard.arrayandstring;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/30.
 * 287. 寻找重复数
 * https://leetcode-cn.com/problems/find-the-duplicate-number/solution/
 */
public class FindDuplicateNumber {
	public int findDuplicate(int[] nums) {
		return 0;
	}

	public static void main(String[] args){
		FindDuplicateNumber main = new FindDuplicateNumber();
		ResultCheck.check(main.findDuplicate(new int[]{1,3,4,2,2}), 2);
		ResultCheck.check(main.findDuplicate(new int[]{3,1,3,4,2}), 3);
	}
}
