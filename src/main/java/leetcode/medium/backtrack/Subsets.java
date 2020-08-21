package leetcode.medium.backtrack;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.List;

/**
 * Created by 曹云 on 2020/8/21.
 * 78. 子集
 * https://leetcode-cn.com/problems/subsets/solution/
 */
public class Subsets {

	public List<List<Integer>> subsets(int[] nums) {
		return null;
	}

	public static void main(String[] args){
		Subsets main = new Subsets();
		ResultCheck.checkTwoDimension(main.subsets(new int[]{1,2,3}), Lists.newArrayList(
				Lists.newArrayList(3),
				Lists.newArrayList(1),
				Lists.newArrayList(2),
				Lists.newArrayList(1,2,3),
				Lists.newArrayList(1,3),
				Lists.newArrayList(2,3),
				Lists.newArrayList(1,2),
				Lists.newArrayList()
		));
	}
}
