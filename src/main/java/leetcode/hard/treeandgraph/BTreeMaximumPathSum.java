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
	public int maxPathSum(TreeNode root) {
		return 0;
	}

	public static void main(String[] args){
		BTreeMaximumPathSum main = new BTreeMaximumPathSum();
		ResultCheck.check(main.maxPathSum(TreeNodeUtil.createTreeNode(new Integer[]{1,2,3})),6);
		ResultCheck.check(main.maxPathSum(TreeNodeUtil.createTreeNode(new Integer[]{-10,9,20,null,null,15,7})),42);
	}
}
