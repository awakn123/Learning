package leetcode.medium.arrayandstr;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.List;

/**
 * Created by 曹云 on 2020/8/18.
 * 15. 3Sum
 * https://leetcode-cn.com/problems/3sum/solution/
 */
public class ThreeSum {
	public List<List<Integer>> threeSum(int[] nums) {
		return null;
	}

	public static void main(String[] args){
		ThreeSum main = new ThreeSum();
		List<List<Integer>> answer = Lists.newArrayList(
				Lists.newArrayList(-1,0,1),
				Lists.newArrayList(-1,-1,2)
		);
		ResultCheck.checkTwoDimension(main.threeSum(new int[]{-1, 0, 1, 2, -1, -4}), answer);
	}
}
