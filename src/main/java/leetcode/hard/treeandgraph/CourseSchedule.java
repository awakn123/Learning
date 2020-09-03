package leetcode.hard.treeandgraph;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/9/3.
 * 207. 课程表
 * https://leetcode-cn.com/problems/course-schedule/solution/
 */
public class CourseSchedule {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		return false;
	}

	public static void main(String[] args){
		CourseSchedule main = new CourseSchedule();
		ResultCheck.check(main.canFinish(2, new int[][]{{1,0}}), true);
		ResultCheck.check(main.canFinish(2, new int[][]{{1,0}, {0,1}}), false);
	}
}
