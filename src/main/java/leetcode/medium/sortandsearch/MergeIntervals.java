package leetcode.medium.sortandsearch;

import leetcode.util.ResultCheck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 曹云 on 2020/8/25.
 * 56. 合并区间
 * https://leetcode-cn.com/problems/merge-intervals/solution/
 */
public class MergeIntervals {

	public int[][] merge(int[][] intervals) {
		if (intervals.length == 0) return intervals;
		int[][] result = new int[intervals.length][];
		int len = 0;
		Arrays.sort(intervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		int[] merge = intervals[0];
		for (int i=1; i<intervals.length; i++) {
			int[] num = intervals[i];
			if (num[0] > merge[1]) {
				result[len++] = merge;
				merge = num;
				continue;
			}
			merge[1] = Math.max(num[1], merge[1]);
		}
		result[len++] = merge;
		return Arrays.copyOf(result, len);
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
