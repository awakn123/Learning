package leetcode.medium.treeandgraph;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;
import leetcode.util.TreeNode;
import leetcode.util.TreeNodeUtil;

import java.util.List;

/**
 * Created by 曹云 on 2020/8/18.
 * 103. 二叉树的锯齿形层次遍历 Binary Tree Zigzag Level Order Traversal.
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/solution/
 */
public class BinaryTreeZigzagLevelOrderTraversal {

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		return null;
	}

	public static void main(String[] args){
		BinaryTreeZigzagLevelOrderTraversal main = new BinaryTreeZigzagLevelOrderTraversal();
		TreeNode root = TreeNodeUtil.createTreeNode(new Integer[]{3,9,20,null,null,15,7});
		List<List<Integer>> answer = Lists.newArrayList(
				Lists.newArrayList(3),
				Lists.newArrayList(20,9),
				Lists.newArrayList(15,7)
			);
		ResultCheck.checkTwoDimension(main.zigzagLevelOrder(root), answer);
	}
}
