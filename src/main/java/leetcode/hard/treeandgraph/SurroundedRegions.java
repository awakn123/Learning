package leetcode.hard.treeandgraph;

import leetcode.util.ResultCheck;

import java.util.Arrays;

/**
 * Created by 曹云 on 2020/9/3.
 * 130. 被围绕的区域
 * https://leetcode-cn.com/problems/surrounded-regions/solution/
 */
public class SurroundedRegions {
	public void solve(char[][] board) {

	}

	public static void main(String[] args){
		SurroundedRegions main = new SurroundedRegions();
		char[][] board = new char[][]{
				"XXXX".toCharArray(),
				"XOOX".toCharArray(),
				"XXOX".toCharArray(),
				"XOXX".toCharArray(),
		};
		char[][] answer = new char[][]{
				"XXXX".toCharArray(),
				"XXXX".toCharArray(),
				"XXXX".toCharArray(),
				"XOXX".toCharArray(),
		};
		main.solve(board);
		System.out.println("-------board:-------");
		for(char[] arr: board)
			System.out.println(Arrays.toString(arr));
		System.out.println("-------answer:-------");
		for(char[] arr: answer)
			System.out.println(Arrays.toString(arr));
	}
}
