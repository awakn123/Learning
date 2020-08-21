package leetcode.medium.treeandgraph;

import com.google.common.collect.Lists;
import leetcode.easy.tree.BinaryTreeLevelOrderTraversal;
import leetcode.util.ResultCheck;
import leetcode.util.TreeNode;
import leetcode.util.TreeNodeUtil;

import java.util.List;

/**
 * Created by 曹云 on 2020/8/18.
 * 94. 二叉树的中序遍历 Binary Tree Inorder Traversal
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/
 */
public class BinaryTreeInorderTraversal {

	public List<Integer> inorderTraversal(TreeNode root) {
		return null;
	}

	public static void main(String[] args){
		BinaryTreeInorderTraversal main = new BinaryTreeInorderTraversal();
		TreeNode root = TreeNodeUtil.createTreeNode(new Integer[]{1,null,2,3});
		ResultCheck.checkList(main.inorderTraversal(root), Lists.newArrayList(1,3,2));
	}
}
