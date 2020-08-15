package leetcode.easy.tree;

import leetcode.util.ResultCheck;
import leetcode.util.TreeNode;
import leetcode.util.TreeNodeUtil;

/**
 * Created by 曹云 on 2020/8/12.
 * 104. Maximum Depth of Binary Tree
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */
public class MaxDepthBinaryTree {
	public int maxDepth(TreeNode root) {
		return 0;
	}

	public static void main(String[] args){
		MaxDepthBinaryTree main = new MaxDepthBinaryTree();
		TreeNode rootI = TreeNodeUtil.createTreeNode(new Integer[]{3,9,20,null,null,15,7});
		int answerI = 3;
		int resultI = main.maxDepth(rootI);
		ResultCheck.check(resultI, answerI);
		TreeNode rootII = TreeNodeUtil.createTreeNode(new Integer[]{3});
		int answerII = 1;
		int resultII = main.maxDepth(rootII);
		ResultCheck.check(resultII, answerII);
	}
}
