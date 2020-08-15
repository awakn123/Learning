package leetcode.easy.tree;

import leetcode.util.ResultCheck;
import leetcode.util.TreeNode;
import leetcode.util.TreeNodeUtil;

/**
 * Created by 曹云 on 2020/8/12.
 * 98. Validate Binary Search Tree
 * https://leetcode.com/problems/validate-binary-search-tree/
 */
public class ValidBST {
	public boolean isValidBST(TreeNode root) {
		return false;
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
		TreeNode rootIII = TreeNodeUtil.createTreeNode(new Integer[]{5,1,7,null,null,3,8});
		boolean resultIII = main.isValidBST(rootIII);
		boolean answerIII = true;
		ResultCheck.check(resultIII, answerIII);
	}
}
