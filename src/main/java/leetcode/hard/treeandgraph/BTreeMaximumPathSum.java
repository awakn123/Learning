package leetcode.hard.treeandgraph;

import leetcode.util.ResultCheck;
import leetcode.util.TreeNode;
import leetcode.util.TreeNodeUtil;

/**
 * Created by 曹云 on 2020/9/3.
 * 124. 二叉树中的最大路径和
 * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/solution/
 */
public class BTreeMaximumPathSum {
	int max = Integer.MIN_VALUE;
	public int maxPathSum(TreeNode root) {
		if (root == null)
			return 0;
		maxSum(root);
		return max;
	}

	private int maxSum(TreeNode root) {
		if (root == null)
			return 0;
		int left = Math.max(maxSum(root.left), 0);
		int right = Math.max(maxSum(root.right), 0);
		int val = root.val + left + right;
		max = Math.max(val, max);
		return root.val + Math.max(left, right);
	}

	public static void main(String[] args){
		BTreeMaximumPathSum main = new BTreeMaximumPathSum();
		ResultCheck.check(main.maxPathSum(TreeNodeUtil.createTreeNode(new Integer[]{1,2,3})),6);
		ResultCheck.check(main.maxPathSum(TreeNodeUtil.createTreeNode(new Integer[]{-10,9,20,null,null,15,7})),42);
		ResultCheck.check(main.maxPathSum(TreeNodeUtil.createTreeNode(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,null,1})),48);
	}
}
