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
		return toBST(nums, 0, nums.length);
	}
	private TreeNode toBST(int[] nums, int left, int right) {
		int len = right - left;
		if (len <= 0)
			return null;
		if (len == 1)
			return new TreeNode(nums[left]);
		int mid = left + (len >> 1);
		TreeNode node = new TreeNode(nums[mid]);
		node.left = toBST(nums, left, mid);
		node.right = toBST(nums, mid + 1, right);
		return node;
	}

	public static void main(String[] args){
		StArrayToBST main = new StArrayToBST();
		int[] numsI = new int[]{-10,-3,0,5,9};
		TreeNode rootI = main.sortedArrayToBST(numsI);
		ResultCheck.checkAVL(rootI);
		ResultCheck.checkBST(rootI);
	}

}
