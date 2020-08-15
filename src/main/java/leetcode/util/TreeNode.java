package leetcode.util;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by 曹云 on 2020/8/12.
 */

public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode() {
	}

	public TreeNode(int val) {
		this.val = val;
	}

	public TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		StringBuffer out = new StringBuffer();
		out.append(this.val);
		List<TreeNode> nodeList = Lists.newArrayList(this.left, this.right);
		while(true) {
			boolean hasValue = false;
			for (TreeNode node : nodeList) {
				if (node != null) hasValue = true;
			}
			if (!hasValue)
				break;
			List<TreeNode> nextNodeList = Lists.newArrayList();
			out.append("\n");
			for (TreeNode node : nodeList) {
				out.append(node == null ? "null" : node.val).append(",");
				nextNodeList.add(node != null ? node.left : null);
				nextNodeList.add(node != null ? node.right : null);
			}
			out.deleteCharAt(out.length() - 1);
			nodeList = nextNodeList;
		}
		return out.toString();
	}
}
