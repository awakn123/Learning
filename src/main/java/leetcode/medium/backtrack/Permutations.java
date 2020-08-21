package leetcode.medium.backtrack;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.List;

/**
 * Created by 曹云 on 2020/8/21.
 * 46. 全排列
 * https://leetcode-cn.com/problems/permutations/solution/
 */
public class Permutations {

	public List<List<Integer>> permute(int[] nums) {
		return null;
	}

	public static void main(String[] args){
		Permutations main = new Permutations();
		ResultCheck.checkTwoDimension(main.permute(new int[]{1,2,3}),
				Lists.newArrayList(
						Lists.newArrayList(1,2,3),
						Lists.newArrayList(1,3,2),
						Lists.newArrayList(2,1,3),
						Lists.newArrayList(2,3,1),
						Lists.newArrayList(3,1,2),
						Lists.newArrayList(3,2,1)
						)
			);
	}
}
