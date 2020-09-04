package leetcode.hard.treeandgraph;

import leetcode.util.ResultCheck;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by 曹云 on 2020/9/3.
 * 210. 课程表 II
 * https://leetcode-cn.com/problems/course-schedule-ii/solution/
 */
public class CourseScheduleII {
	public int[] findOrder1(int numCourses, int[][] prerequisites) {
		int[] result = new int[numCourses];
		int idx = 0;
		// initialize directed edge.
		List<List<Integer>> directedEdges = new ArrayList<>();
		for (int i=0;i<numCourses;i++) {
			directedEdges.add(new ArrayList<>());
		}

		// initialize indeg num.
		int[] indeg = new int[numCourses];
		for (int[] prereq: prerequisites) {
			directedEdges.get(prereq[1]).add(prereq[0]);
			indeg[prereq[0]]++;
		}

		// 0 deg's node queue.
		Queue<Integer> queue = new LinkedList<>();
		for (int i=0; i<numCourses; i++) {
			if (indeg[i] == 0)
				queue.offer(i);
		}

		while(!queue.isEmpty()) {
			int num = queue.poll();
			result[idx] = num;
			idx++;
			for (Integer endNum: directedEdges.get(num)) {
				if (--indeg[endNum] == 0) {
					queue.offer(endNum);
				}
			}
		}

		if (idx != numCourses)
			return new int[0];

		return result;
	}

	boolean isLegal = true;
	List<List<Integer>> directedEdges;
	int[] visited;// 0:undo 1:searching 2searched
	int[] result;
	int idx;

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		result = new int[numCourses];
		idx = numCourses - 1;
		// store the visited status of node.
		visited = new int[numCourses];
		// initialize the directed edges.
		directedEdges = new ArrayList<>();
		for (int i=0; i<numCourses; i++) {
			directedEdges.add(new ArrayList<>());
		}

		for (int[] prereq: prerequisites) {
			directedEdges.get(prereq[1]).add(prereq[0]);
		}

		for (int i=0; i<numCourses; i++) {
			dfs(i);
			if (!isLegal)
				return new int[0];
		}

		return result;
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
		directedEdges.get(num).forEach((n) -> dfs(n));
		if (!isLegal)
			return;
		visited[num] = 2;
		result[idx--] = num;
	}

	public static void main(String[] args){
		CourseScheduleII main = new CourseScheduleII();
		ResultCheck.check(main.findOrder(2, new int[][]{{1,0}}), new int[]{0,1});
		ResultCheck.check(main.findOrder(4, new int[][]{{1,0}, {2,0}, {3,1}, {3,2}}), new int[]{0,1,2,3});
	}
}
