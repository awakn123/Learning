package leetcode.medium.treeandgraph;

import leetcode.util.ResultCheck;
import leetcode.util.TreeNode;
import leetcode.util.TreeNodeUtil;

/**
 * Created by 曹云 on 2020/8/18.
 * 105. 从前序与中序遍历序列构造二叉树 Construct Binary Tree from Preorder and Inorder Traversal.
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/
 */
public class ConstructBinaryTree {

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return null;
	}
	public static void main(String[] args){
		ConstructBinaryTree main = new ConstructBinaryTree();
		ResultCheck.check(main.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7}), TreeNodeUtil.createTreeNode(new Integer[]{3,9,null,null,20,15,7}));
	}
}
