package leetcode.hard.treeandgraph;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/9/3.
 * 547. 朋友圈
 * https://leetcode-cn.com/problems/friend-circles/solution/
 */
public class FriendCircles {
	public int findCircleNum(int[][] M) {
		return 0;
	}

	public static void main(String[] args){
		FriendCircles main = new FriendCircles();

		ResultCheck.check(main.findCircleNum(new int[][]{
				{1,1,0},
				{1,1,0},
				{0,0,1}
		}), 2);
		ResultCheck.check(main.findCircleNum(new int[][]{
				{1,1,0},
				{1,1,1},
				{0,1,1}
		}), 1);
	}
}
