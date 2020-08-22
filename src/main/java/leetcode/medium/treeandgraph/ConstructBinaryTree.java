package leetcode.medium.treeandgraph;

import leetcode.util.ResultCheck;
import leetcode.util.TreeNode;
import leetcode.util.TreeNodeUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by 曹云 on 2020/8/18.
 * 105. 从前序与中序遍历序列构造二叉树 Construct Binary Tree from Preorder and Inorder Traversal.
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/
 */
public class ConstructBinaryTree {
	/**
	 * 题目蛮难的，整理一下思路。以下是说明示例：
	 * 前序遍历 preorder = [3,9,20,15,7]
	 * 中序遍历 inorder = [9,3,15,20,7]
	 */

	/**
	 * 前序遍历的性质: 前序遍历一定由 = [父节点 [左子树] [右子树]]的形式组成。
	 * 中序遍历的性质: 中序遍历一定由 = [[左子树] 父节点 [右子树]]的形式组成。
	 * 所以，在前序中获得父节点的值，再由中序中获得父节点的位置，从而得出左子树、右子树的长度，递归处理左右子树即可。
	 * 时间复杂度：O(n) 两次遍历，一次形成hashmap，第二次递归构造树。
	 * 空间复杂度：O(n) hashmap存储位置，以及O(h)存储递归的栈空间。
	 * @param preorder
	 * @param inorder
	 * @return
	 */
	public TreeNode buildTree1(int[] preorder, int[] inorder) {

		Map<Integer, Integer> inorderIdx = new HashMap<>(inorder.length);
		for (int i=0;i<inorder.length;i++) {
			inorderIdx.put(inorder[i],i);
		}
		TreeNode result = buildTree(preorder, 0, inorder.length-1, 0, inorder.length - 1, inorderIdx);
		return result;
	}

	private TreeNode buildTree(int[] preorder, int preleft, int preright, int inleft, int inright, Map<Integer, Integer> inorderIdx) {
		if (preleft>preright) return null;
		TreeNode root = new TreeNode(preorder[preleft]);
		int inorderRootIdx = inorderIdx.get(preorder[preleft]);
		int leftLen = inorderRootIdx - inleft;
		root.left = buildTree(preorder, preleft + 1, preleft + leftLen, inleft, inleft + leftLen - 1, inorderIdx);
		int rightLen = inright - inorderRootIdx;
		root.right = buildTree(preorder, preright - rightLen + 1, preright, inright - rightLen + 1, inright, inorderIdx);
		return root;
	}

	/**
	 * 根据上面前序、中序的性质，可以得出以下结论：
	 * 前序是由[[根节点的左子串]-[离最左侧最近的右子树的左子串]...]形成的。
	 * 那么可知，中序遍历是由[最左节点,次左节点,...,离最左侧最近的右节点的中序遍历,...]形成的。
	 * 根节点的左子串以逆序的形式在中序遍历中存在，而右节点，可以通过判断中序中何时出现了不属于逆序左子串的值，来确认右节点的父节点。
	 * @param preorder
	 * @param inorder
	 * @return
	 */
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if (preorder == null || preorder.length == 0) return null;
		TreeNode root = new TreeNode(preorder[0]);
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		int j = 0;
		for (int i=1; i<preorder.length; i++) {
			TreeNode node = stack.peek();
			if (node.val != inorder[j]) {
				node.left = new TreeNode(preorder[i]);
				stack.push(node.left);
			} else {
				while(!stack.isEmpty() && stack.peek().val == inorder[j]) {
					node = stack.pop();
					j++;
				}
				node.right = new TreeNode(preorder[i]);
				stack.push(node.right);
			}
		}
		return root;
	}
	public static void main(String[] args){
		ConstructBinaryTree main = new ConstructBinaryTree();
		System.out.println(main.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7}));
		System.out.println();
		System.out.println(TreeNodeUtil.createTreeNode(new Integer[]{3,9,20,null,null,15,7}));
		System.out.println();
		System.out.println("+++++++++++++");
		System.out.println();
		System.out.println(main.buildTree(new int[]{3,20,15,7}, new int[]{3,15,20,7}));
		System.out.println();
		System.out.println(TreeNodeUtil.createTreeNode(new Integer[]{3,null,20,15,7}));
	}
}
