package leetcode.medium.other;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/29.
 * 169. 多数元素
 * https://leetcode-cn.com/problems/majority-element/solution/
 */
public class MajorityElement {
	public int majorityElement(int[] nums) {
		return 0;
	}

	public static void main(String[] args){
		MajorityElement main = new MajorityElement();
		ResultCheck.check(main.majorityElement(new int[]{3,2,3}), 3);
		ResultCheck.check(main.majorityElement(new int[]{2,2,1,1,1,2,2}), 2);
	}
}
