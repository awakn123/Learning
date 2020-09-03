package leetcode.hard.treeandgraph;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/9/3.
 * 210. 课程表 II
 * https://leetcode-cn.com/problems/course-schedule-ii/solution/
 */
public class CourseScheduleII {
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		return null;
	}

	public static void main(String[] args){
		CourseScheduleII main = new CourseScheduleII();
		ResultCheck.check(main.findOrder(2, new int[][]{{1,0}}), new int[]{0,1});
		ResultCheck.check(main.findOrder(4, new int[][]{{1,0}, {2,0}, {3,1}, {3,2}}), new int[]{0,1,2,3});
	}
}
