package leetcode.medium.treeandgraph;

import com.google.common.collect.Lists;
import leetcode.easy.tree.BinaryTreeLevelOrderTraversal;
import leetcode.util.ResultCheck;
import leetcode.util.TreeNode;
import leetcode.util.TreeNodeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by 曹云 on 2020/8/18.
 * 94. 二叉树的中序遍历 Binary Tree Inorder Traversal
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/
 */
public class BinaryTreeInorderTraversal {

	public List<Integer> inorderTraversal1(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		inorder(root, result);
		return result;
	}

	private void inorder(TreeNode node, List<Integer> list) {
		if (node == null) return;
		inorder(node.left, list);
		list.add(node.val);
		inorder(node.right, list);
	}

	public List<Integer> inorderTraversal2(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode node = root;
		boolean hasAdded = false;
		while(node != null) {
			if (!hasAdded && node.left != null) {
				stack.push(node);
				node = node.left;
			} else {
				result.add(node.val);
				if (node.right != null) {
					node = node.right;
					hasAdded = false;
				} else {
					node = stack.isEmpty() ? null : stack.pop();
					hasAdded = true;
				}
			}
		}
		return result;
	}
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		while(root != null || !stack.isEmpty()) {
			while(root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			result.add(root.val);
			root = root.right;
		}
		return result;
	}

	public static void main(String[] args){
		BinaryTreeInorderTraversal main = new BinaryTreeInorderTraversal();
		TreeNode root = TreeNodeUtil.createTreeNode(new Integer[]{1,null,2,3});
		ResultCheck.checkList(main.inorderTraversal(root), Lists.newArrayList(1,3,2));
	}
}
