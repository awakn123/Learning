package leetcode.medium.design;

import leetcode.util.ResultCheck;
import leetcode.util.TreeNode;
import leetcode.util.TreeNodeUtil;
import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by 曹云 on 2020/8/27.
 * 297. 二叉树的序列化与反序列化
 * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/solution/
 */
public class SerializeAndDeserializeBTree {
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if (root == null) return "[]";
		StringBuilder out = new StringBuilder("[");
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			int size = queue.size();
			boolean allNull = true;
			while(size > 0) {
				TreeNode node = queue.poll();
				if (node == null) {
					out.append("null,");
				} else {
					out.append(node.val).append(",");
					queue.offer(node.left);
					queue.offer(node.right);
					if (node.left != null || node.right != null)
						allNull = false;
				}
				size--;
			}
			if (allNull)
				queue.clear();
		}
		out.deleteCharAt(out.length() - 1).append("]");
		return out.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if (data == null || data.length() < 3) return null;
		String[] numStrs = data.substring(1, data.length() - 1).split(",");
		if (numStrs.length == 0) return null;
		TreeNode root = createTreeNode(numStrs[0]);
		Queue<TreeNode> queue = new LinkedList<TreeNode>(){{add(root);}};
		int i = 1;
		while(i < numStrs.length - 1) {
			TreeNode node = queue.poll();
			if (node == null) {
				continue;
			}
			node.left = createTreeNode(numStrs[i++]);
			node.right = createTreeNode(numStrs[i++]);
			queue.add(node.left);
			queue.add(node.right);
		}

		return root;
	}

	private TreeNode createTreeNode(String numStr) {
		if ("null".equals(numStr)) return null;
		try {
			int num = Integer.parseInt(numStr);
			return new TreeNode(num);
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args){
		SerializeAndDeserializeBTree main = new SerializeAndDeserializeBTree();
		TreeNode node = TreeNodeUtil.createTreeNode(new Integer[]{1,2,3, null, null, 4, 5});
		String str = main.serialize(node);
		ResultCheck.check(str,"[1,2,3,null,null,4,5]");
		ResultCheck.check(main.deserialize(str), node);
		TreeNode nodeII = TreeNodeUtil.createTreeNode(new Integer[]{1,3, null, null, 4});
		String strII = main.serialize(nodeII);
		ResultCheck.check(strII,"[1,3,null,null,4]");
		ResultCheck.check(main.deserialize(strII), nodeII);
		TreeNode nodeIII = TreeNodeUtil.createTreeNode(new Integer[]{1,2,null,3,null,4,null,5});
		String strIII = main.serialize(nodeIII);
		ResultCheck.check(strIII,"[1,2,null,3,null,4,null,5]");
		ResultCheck.check(main.deserialize(strIII), nodeIII);
	}
}
