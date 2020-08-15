package leetcode.easy.tree;

import leetcode.util.ResultCheck;
import leetcode.util.TreeNode;
import leetcode.util.TreeNodeUtil;

/**
 * Created by 曹云 on 2020/8/12.
 * 101. Symmetric Tree
 * https://leetcode.com/problems/symmetric-tree/
 */
public class SymmetricTree {

	public boolean isSymmetric(TreeNode root) {
		return false;
	}

	public static void main(String[] args){
		SymmetricTree main = new SymmetricTree();
		TreeNode rootI = TreeNodeUtil.createTreeNode(new Integer[]{1,2,2,3,4,4,3});
		ResultCheck.check(main.isSymmetric(rootI), true);
		TreeNode rootII = TreeNodeUtil.createTreeNode(new Integer[]{1,2,2,null,3,null,3});
		ResultCheck.check(main.isSymmetric(rootII), false);
	}
}
