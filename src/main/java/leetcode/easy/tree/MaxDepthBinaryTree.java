package leetcode.easy.tree;

import com.google.common.collect.Queues;
import leetcode.util.ResultCheck;
import leetcode.util.TreeNode;
import leetcode.util.TreeNodeUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by 曹云 on 2020/8/12.
 * 104. Maximum Depth of Binary Tree
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */
public class MaxDepthBinaryTree {
	public int maxDepth(TreeNode root) {
		if (root == null) return 0;
		List<TreeNode> nodeList = new ArrayList<>();
		nodeList.add(root);
		int i=0;
		while(!nodeList.isEmpty()) {
			i++;
			List<TreeNode> nextNodeList = new ArrayList<>();
			for (TreeNode node : nodeList) {
				if (node.left != null) nextNodeList.add(node.left);
				if (node.right != null) nextNodeList.add(node.right);
			}
			nodeList = nextNodeList;
		}
		return i;
	}

	public int maxDepth2(TreeNode root) {
		if (root == null) return 0;
		return Math.max(maxDepth2(root.left), maxDepth2(root.right)) + 1;
	}

	public int maxDepth3(TreeNode root) {
		if (root == null) return 0;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		int depth = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size > 0) {
				TreeNode node = queue.poll();
				if (node.left != null) queue.offer(node.left);
				if (node.right != null) queue.offer(node.right);
				size--;
			}
			depth++;
		}
		return depth;
	}

	public static void main(String[] args){
		MaxDepthBinaryTree main = new MaxDepthBinaryTree();
		TreeNode rootI = TreeNodeUtil.createTreeNode(new Integer[]{3,9,20,null,null,15,7});
		int answerI = 3;
		int resultI = main.maxDepth3(rootI);
		ResultCheck.check(resultI, answerI);
		TreeNode rootII = TreeNodeUtil.createTreeNode(new Integer[]{3});
		int answerII = 1;
		int resultII = main.maxDepth3(rootII);
		ResultCheck.check(resultII, answerII);
	}
}
