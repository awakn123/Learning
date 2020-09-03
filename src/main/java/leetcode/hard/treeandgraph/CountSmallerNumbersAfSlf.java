package leetcode.hard.treeandgraph;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.List;

/**
 * Created by 曹云 on 2020/9/3.
 * 315. 计算右侧小于当前元素的个数
 * https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/solution/
 */
public class CountSmallerNumbersAfSlf {
	public List<Integer> countSmaller(int[] nums) {
		return null;
	}

	public static void main(String[] args){
		CountSmallerNumbersAfSlf main = new CountSmallerNumbersAfSlf();
		ResultCheck.checkList(main.countSmaller(new int[]{5,2,6,1}), Lists.newArrayList(2,1,1,0));
	}
}
