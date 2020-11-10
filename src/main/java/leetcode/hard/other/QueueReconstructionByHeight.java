package leetcode.hard.other;

import leetcode.util.ResultCheck;

import java.util.*;

/**
 * Created by 曹云 on 2020/11/10.
 * https://leetcode-cn.com/problems/queue-reconstruction-by-height/
 * 406. 根据身高重建队列
 */
public class QueueReconstructionByHeight {
	public int[][] reconstructQueue(int[][] people) {
		Arrays.sort(people, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
			}
		});
		List<int[]> sortList = new LinkedList<>();
		for (int[] arr: people) {
			sortList.add(arr[1], arr);
		}
		return sortList.toArray(new int[people.length][2]);
	}

	public static void main(String[] args){
		QueueReconstructionByHeight main = new QueueReconstructionByHeight();
		ResultCheck.checkTwoDimension(main.reconstructQueue(new int[][]{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}}),
				new int[][]{{5,0}, {7,0}, {5,2}, {6,1}, {4,4}, {7,1}}
		);
	}
}
