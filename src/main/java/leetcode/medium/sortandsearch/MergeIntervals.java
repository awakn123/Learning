package leetcode.medium.sortandsearch;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/25.
 * 56. 合并区间
 * https://leetcode-cn.com/problems/merge-intervals/solution/
 */
public class MergeIntervals {

	public int[][] merge(int[][] intervals) {
		return intervals;
	}

	public static void main(String[] args){
		MergeIntervals main = new MergeIntervals();
		int[][] intervalsI = new int[][]{
				new int[]{1,3},
				new int[]{2,6},
				new int[]{8,10},
				new int[]{15,18}
		};
		int[][] answerI = new int[][]{
				new int[]{1,6},
				new int[]{8,10},
				new int[]{15,18}
		};
		ResultCheck.checkTwoDimension(main.merge(intervalsI),answerI);
		int[][] intervalsII = new int[][]{
				new int[]{1,4},
				new int[]{4,5},
		};
		int[][] answerII = new int[][]{
				new int[]{1,5},
		};
		ResultCheck.checkTwoDimension(main.merge(intervalsII),answerII);
	}
}
