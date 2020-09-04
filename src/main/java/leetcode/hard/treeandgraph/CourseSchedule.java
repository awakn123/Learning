package leetcode.hard.treeandgraph;

import leetcode.util.ResultCheck;

import java.util.*;

/**
 * Created by 曹云 on 2020/9/3.
 * 207. 课程表
 * https://leetcode-cn.com/problems/course-schedule/solution/
 */
public class CourseSchedule {
	public boolean canFinish1(int numCourses, int[][] prerequisites) {
		int[] indeg = new int[numCourses];
		List<List<Integer>> directedEdges = new ArrayList<>();
		for (int i=0; i<numCourses; i++) {
			directedEdges.add(new ArrayList<>());
		}
		for (int[] prereq: prerequisites) {
			directedEdges.get(prereq[1]).add(prereq[0]);
			indeg[prereq[0]]++;
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int i=0; i<numCourses; i++) {
			if (indeg[i] == 0)
				queue.offer(i);
		}

		while(!queue.isEmpty()) {
			int num = queue.poll();
			for (int i: directedEdges.get(num)) {
				if (--indeg[i] == 0)
					queue.offer(i);
			}
		}

		for (int i=0;i<numCourses;i++) {
			if (indeg[i] != 0)
				return false;
		}

		return true;
	}

	List<List<Integer>> directedEdges;
	int[] visited;// 0: unsearch; 1: searching; 2: searched.
	boolean isLegal = true;
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		visited = new int[numCourses];
		directedEdges = new ArrayList<>();
		for (int i=0; i<numCourses; i++) {
			directedEdges.add(new ArrayList<>());
		}

		for (int[] prereq: prerequisites) {
			directedEdges.get(prereq[1]).add(prereq[0]);
		}

		for (int i=0;i<numCourses && isLegal;i++) {
			dfs(i);
		}
		return isLegal;
	}

	private void dfs(int num) {
		if (!isLegal)
			return;
		if (visited[num] == 2)
			return;
		if (visited[num] == 1) {
			isLegal = false;
			return;
		}
		visited[num] = 1;
		directedEdges.get(num).forEach(this::dfs);
		visited[num] = 2;
	}
	public static void main(String[] args){
		CourseSchedule main = new CourseSchedule();
		ResultCheck.check(main.canFinish(2, new int[][]{{1,0}}), true);
		ResultCheck.check(main.canFinish(2, new int[][]{{1,0}, {0,1}}), false);
	}
}
