package leetcode.hard.sordandsearch;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/9/9.
 * 324. 摆动排序 II
 * https://leetcode-cn.com/problems/wiggle-sort-ii/solution/
 */
public class WiggleSortII {
	public void wiggleSort(int[] nums) {

	}

	public static void main(String[] args){
		WiggleSortII main = new WiggleSortII();
		int[] numsI = new int[]{1,5,1,1,6,4};
		main.wiggleSort(numsI);
		ResultCheck.check(numsI, new int[]{1,4,1,5,1,6});
	}
}
