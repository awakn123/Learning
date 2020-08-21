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

	public int kthSmallest(TreeNode root, int k) {
		return 0;
	}

	public static void main(String[] args){
		KthSmallestElementBST main = new KthSmallestElementBST();
		TreeNode rootI = TreeNodeUtil.createTreeNode(new Integer[]{3,1,4,null,2});
		ResultCheck.check(main.kthSmallest(rootI, 1), 1);
		TreeNode rootII = TreeNodeUtil.createTreeNode(new Integer[]{5,3,6,2,4,null,null,1});
		ResultCheck.check(main.kthSmallest(rootII, 3), 3);
	}
}
