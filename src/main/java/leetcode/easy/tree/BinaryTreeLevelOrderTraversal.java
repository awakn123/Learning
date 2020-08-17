package leetcode.easy.tree;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;
import leetcode.util.TreeNode;
import leetcode.util.TreeNodeUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by 曹云 on 2020/8/12.
 * 102. Binary Tree Level Order Traversal
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 */
public class BinaryTreeLevelOrderTraversal {
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()) {
			int size = queue.size();
			List<Integer> level = new ArrayList<>();
			while(size > 0) {
				TreeNode node = queue.poll();
				if (node != null) {
					level.add(node.val);
					queue.offer(node.left);
					queue.offer(node.right);
				}
				size--;
			}
			if (!level.isEmpty())
				result.add(level);
		}
		return result;
	}

	public static void main(String[] args){
		BinaryTreeLevelOrderTraversal main = new BinaryTreeLevelOrderTraversal();
		TreeNode rootI = TreeNodeUtil.createTreeNode(new Integer[]{3,9,20,null,null,15,7});
		List<List<Integer>> answerI = Lists.newArrayList(Lists.newArrayList(3), Lists.newArrayList(9,20), Lists.newArrayList(15,7));
		ResultCheck.checkTwoDimension(main.levelOrder(rootI), answerI);
	}
}
