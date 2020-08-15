package leetcode.easy.tree;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;
import leetcode.util.TreeNode;
import leetcode.util.TreeNodeUtil;

import java.util.List;

/**
 * Created by 曹云 on 2020/8/12.
 * 102. Binary Tree Level Order Traversal
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 */
public class BinaryTreeLevelOrderTraversal {
	public List<List<Integer>> levelOrder(TreeNode root) {
		return null;
	}

	public static void main(String[] args){
		BinaryTreeLevelOrderTraversal main = new BinaryTreeLevelOrderTraversal();
		TreeNode rootI = TreeNodeUtil.createTreeNode(new Integer[]{3,9,20,null,null,15,7});
		List<List<Integer>> answerI = Lists.newArrayList(Lists.newArrayList(3), Lists.newArrayList(9,20), Lists.newArrayList(15,7));
		ResultCheck.check(main.levelOrder(rootI), answerI);
	}
}
