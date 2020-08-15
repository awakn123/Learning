package leetcode.util;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by 曹云 on 2020/8/15.
 */
public class TreeNodeUtil {

	public static TreeNode createTreeNode(Integer[] vals) {
		if (vals == null || vals.length == 0) return null;
		TreeNode root = new TreeNode(vals[0]);
		int i=0;
		List<TreeNode> nodeList = Lists.newArrayList(root);
		while(i<vals.length) {
			List<TreeNode> nextNodeList = Lists.newArrayList();
			for (TreeNode node: nodeList) {
				if (node == null) continue;;
				Integer left = (++i) < vals.length ? vals[i] : null;
				Integer right = (++i) < vals.length ? vals[i] : null;
				node.left = left != null ? new TreeNode(left) : null;
				node.right = right != null ? new TreeNode(right) : null;
				nextNodeList.add(node.left);
				nextNodeList.add(node.right);
			}
			nodeList = nextNodeList;
		}
		return root;
	}

}
