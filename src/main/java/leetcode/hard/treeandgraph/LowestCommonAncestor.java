package leetcode.hard.treeandgraph;

import leetcode.util.TreeNode;
import leetcode.util.TreeNodeUtil;

/**
 * Created by 曹云 on 2020/9/3.
 * 236. 二叉树的最近公共祖先
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/
 */
public class LowestCommonAncestor {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		return null;
	}

	public static void main(String[] args){
		LowestCommonAncestor main = new LowestCommonAncestor();
		TreeNode root = TreeNodeUtil.createTreeNode(new Integer[]{3,5,1,6,2,0,8,null,null,7,4});
		TreeNode result = main.lowestCommonAncestor(root, root.left, root.right);
		System.out.println("result is right:" + (result == root));
		TreeNode resultII = main.lowestCommonAncestor(root, root.left, root.left.right.right);
		System.out.println("resultII is right:" + (resultII == root.left));
	}
}
