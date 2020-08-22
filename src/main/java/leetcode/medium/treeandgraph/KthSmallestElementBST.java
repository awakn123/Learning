package leetcode.medium.treeandgraph;

import leetcode.util.ResultCheck;
import leetcode.util.TreeNode;
import leetcode.util.TreeNodeUtil;

/**
 * Created by 曹云 on 2020/8/18.
 * 230. 二叉搜索树中第K小的元素
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/solution/
 */
public class KthSmallestElementBST {

	int i = 0;
	int k = 1;
	int r = 0;
	public int kthSmallest(TreeNode root, int k) {
		this.k = k;
		dfs(root);
		return r;
	}

	private void dfs(TreeNode node) {
		if (node == null) return;
		if (i == k) return;
		dfs(node.left);
		i++;
		if (i == k)
			r = node.val;
		dfs(node.right);
	}

	public static void main(String[] args){
		KthSmallestElementBST main = new KthSmallestElementBST();
		TreeNode rootI = TreeNodeUtil.createTreeNode(new Integer[]{3,1,4,null,2});
		ResultCheck.check(main.kthSmallest(rootI, 1), 1);
		main.i = 0;
		TreeNode rootII = TreeNodeUtil.createTreeNode(new Integer[]{5,3,6,2,4,null,null,1});
		ResultCheck.check(main.kthSmallest(rootII, 3), 3);
	}
}
