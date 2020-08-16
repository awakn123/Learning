package leetcode.easy.tree;

import leetcode.util.ResultCheck;
import leetcode.util.TreeNode;
import leetcode.util.TreeNodeUtil;

import java.util.*;

/**
 * Created by 曹云 on 2020/8/12.
 * 98. Validate Binary Search Tree
 * https://leetcode.com/problems/validate-binary-search-tree/
 */
public class ValidBST {
	public boolean isValidBST2(TreeNode root) {
		return isValidBST(root, null, null);
	}

	public boolean isValidBST(TreeNode root, Integer min, Integer max) {
		if (root == null) return true;
		if (min != null && root.val <= min) return false;
		if (max != null && root.val >= max) return false;
		return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
	}

	public boolean isValidBST(TreeNode root) {
		if (root == null) return true;
		Stack<TreeNode> stack = new Stack<>();
		Integer val = null;
		do {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			if (val != null && val >= root.val) return false;
			val = root.val;
			root = root.right;
		} while (!stack.isEmpty() || root != null);
		return true;
	}

	public static void main(String[] args){
		ValidBST main = new ValidBST();
		TreeNode rootI = TreeNodeUtil.createTreeNode(new Integer[]{2,1,3});
		boolean resultI = main.isValidBST(rootI);
		boolean answerI = true;
		ResultCheck.check(resultI, answerI);
		TreeNode rootII = TreeNodeUtil.createTreeNode(new Integer[]{5,1,4,null,null,3,6});
		boolean resultII = main.isValidBST(rootII);
		boolean answerII = false;
		ResultCheck.check(resultII, answerII);
		TreeNode rootIII = TreeNodeUtil.createTreeNode(new Integer[]{5,1,7,null,null,6,8});
		boolean resultIII = main.isValidBST(rootIII);
		boolean answerIII = true;
		ResultCheck.check(resultIII, answerIII);
		TreeNode rootIV = TreeNodeUtil.createTreeNode(new Integer[]{3,1,5,0,2,4,6,null,null,null,3});
		boolean resultIV = main.isValidBST(rootIV);
		boolean answerIV = false;
		ResultCheck.check(resultIV, answerIV);
		TreeNode rootV = TreeNodeUtil.createTreeNode(new Integer[]{-2147483648,null,2147483647});
		boolean resultV = main.isValidBST(rootV);
		boolean answerV = true;
		ResultCheck.check(resultV, answerV);
	}
}
