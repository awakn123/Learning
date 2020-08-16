package leetcode.easy.tree;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;
import leetcode.util.TreeNode;
import leetcode.util.TreeNodeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 曹云 on 2020/8/12.
 * 101. Symmetric Tree
 * https://leetcode.com/problems/symmetric-tree/
 */
public class SymmetricTree {

	public boolean isSymmetric2(TreeNode root) {
		if (root == null) return true;
		List<TreeNode> nodeList = new ArrayList<>();
		nodeList.add(root);
		while(!nodeList.isEmpty()) {
			List<TreeNode> nextNodeList = new ArrayList<>();
			int halfLength = nodeList.size()>>1;
			for (int i=0; i<nodeList.size(); i++) {
				TreeNode leftNode = nodeList.get(i);
				if (i<halfLength) {
					TreeNode rightNode = nodeList.get(nodeList.size() - 1 - i);
					if (leftNode != rightNode) {
						if (leftNode == null || rightNode == null)
							return false;
						if (leftNode.val != rightNode.val)
							return false;
					}
				}
				addChildren(leftNode, nextNodeList);
			}
			nodeList = nextNodeList;
		}
		return true;
	}

	private void addChildren(TreeNode node, List<TreeNode> nodeList) {
		if (node == null) return;
		nodeList.add(node.left);
		nodeList.add(node.right);
	}
	public boolean isSymmetric(TreeNode root) {
		if (root == null) return true;
		return this.check(root.left, root.right);
	}

	public boolean check(TreeNode left, TreeNode right) {
		if (left == right) return true;
		if (left == null || right == null) return false;
		return left.val == right.val && check(left.left, right.right) && check(left.right, right.left);
	}
	public static void main(String[] args){
		SymmetricTree main = new SymmetricTree();
		TreeNode rootI = TreeNodeUtil.createTreeNode(new Integer[]{1,2,2,3,4,4,3});
		ResultCheck.check(main.isSymmetric(rootI), true);
		TreeNode rootII = TreeNodeUtil.createTreeNode(new Integer[]{1,2,2,null,3,null,3});
		ResultCheck.check(main.isSymmetric(rootII), false);
		TreeNode rootIII = TreeNodeUtil.createTreeNode(new Integer[]{2,3,3,4,5,5,4,null,null,8,9,9,8});
		ResultCheck.check(main.isSymmetric(rootIII), true);
	}
}
