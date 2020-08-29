package leetcode.medium.other;

import leetcode.util.ResultCheck;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 曹云 on 2020/8/29.
 * 621. 任务调度器
 * https://leetcode-cn.com/problems/task-scheduler/solution/
 */
public class TaskScheduler {
	public int leastInterval1(char[] tasks, int n) {
		int r = 0;
		int[] taskNum = new int[26];
		for (char t: tasks) {
			taskNum[t-'A']++;
		}
		Arrays.sort(taskNum);
		while(taskNum[25] > 0) {
			int i = 0;
			while (i <= n) {
				if (taskNum[25] == 0)
					break;
				if (i<=25 && taskNum[25-i] > 0)
					taskNum[25-i]--;
				i++;
				r++;
			}
			Arrays.sort(taskNum);
		}
		return r;
	}
	public int leastInterval(char[] tasks, int n) {
		int idle = 0;
		int[] taskNum = new int[26];
		for (char t: tasks) {
			taskNum[t-'A']++;
		}
		Arrays.sort(taskNum);
		int max = taskNum[25] - 1;
		idle = max * n;
		for (int i=0;i<25;i++) {
			idle -= Math.min(max, taskNum[i]);
		}
		return tasks.length + (idle > 0? idle : 0);
	}
	public static void main(String[] args){
		TaskScheduler main = new TaskScheduler();
		ResultCheck.check(main.leastInterval(new char[]{'A','A','A','B','B','B'}, 2), 8);
	}
}
