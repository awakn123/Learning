package leetcode.medium.treeandgraph;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;
import leetcode.util.TreeNode;
import leetcode.util.TreeNodeUtil;

import java.util.*;

/**
 * Created by 曹云 on 2020/8/18.
 * 103. 二叉树的锯齿形层次遍历 Binary Tree Zigzag Level Order Traversal.
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/solution/
 */
public class BinaryTreeZigzagLevelOrderTraversal {

	public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		stack.add(root);
		boolean leftFirst = true;
		while(!stack.isEmpty()) {
			int size = stack.size();
			List<Integer> list = new ArrayList<>(size);
			Stack<TreeNode> nextStack = new Stack<>();
			while(size > 0) {
				size--;
				TreeNode node = stack.pop();
				if (node == null)
					continue;
				list.add(node.val);
				nextStack.push(leftFirst ? node.left:node.right);
				nextStack.push(leftFirst ? node.right:node.left);
			}
			leftFirst = !leftFirst;
			if (list.size() > 0)
				result.add(list);
			stack = nextStack;
		}
		return result;
	}

	/**
	 * 本题实际考查的是双端队列的使用，所以我还是重新实现一下吧。
	 * @param root
	 * @return
	 */
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		boolean leftFirst = true;
		while(!queue.isEmpty()) {
			int size = queue.size();
			LinkedList<Integer> list = new LinkedList<>();
			while(size > 0) {
				size--;
				TreeNode node = queue.poll();
				if (node == null)
					continue;
				queue.offer(node.left);
				queue.offer(node.right);
				if (leftFirst) {
					list.addLast(node.val);
				} else {
					list.addFirst(node.val);
				}
			}
			leftFirst = !leftFirst;
			if (list.size() > 0)
				result.add(list);
		}
		return result;
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
		TreeNode rootI = TreeNodeUtil.createTreeNode(new Integer[]{1,2,3,4,null,null,5});
		List<List<Integer>> answerI = Lists.newArrayList(
				Lists.newArrayList(1),
				Lists.newArrayList(3,2),
				Lists.newArrayList(4,5)
		);
		ResultCheck.checkTwoDimension(main.zigzagLevelOrder(rootI), answerI);
	}
}
