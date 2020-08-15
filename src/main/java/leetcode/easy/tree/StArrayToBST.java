package leetcode.easy.tree;

import leetcode.util.ResultCheck;
import leetcode.util.TreeNode;

/**
 * Created by 曹云 on 2020/8/12.
 * 108. Convert Sorted Array to Binary Search Tree
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class StArrayToBST {
	public TreeNode sortedArrayToBST(int[] nums) {
		return null;
	}

	public static void main(String[] args){
		StArrayToBST main = new StArrayToBST();
		int[] numsI = new int[]{-10,-3,0,5,9};
		TreeNode rootI = main.sortedArrayToBST(numsI);
		ResultCheck.checkAVL(rootI);
	}

}
