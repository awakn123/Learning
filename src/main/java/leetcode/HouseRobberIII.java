package leetcode;


import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by 曹云 on 2020/8/5.
 * 337. House Robber III
 * https://leetcode.com/problems/house-robber-iii/
 */
public class HouseRobberIII {
	public int rob(TreeNode root) {
		if (root == null)
			return 0;
		int robMoney = 0;

		return robMoney;
	}
	public static void main(String[] args){
		Integer[] rootArrayI = {3,2,3,null,3,null,1};
		int answerI = 7;
		Integer[] rootArrayII = {3,4,5,1,3,null,1};
		int answerII = 9;
		TreeNode rootNodeI = arrayToTree(rootArrayI, 0);
		TreeNode rootNodeII = arrayToTree(rootArrayII, 0);
		HouseRobberIII main = new HouseRobberIII();
		int resultI = main.rob(rootNodeI);
		int resultII = main.rob(rootNodeII);
		checkResult(resultI, answerI);
		checkResult(resultII, answerII);
	}

	public static void checkResult(int result, int answer) {
		if (result == answer)
			System.out.println("Pass");
		else
			System.out.printf("Wrong, result is %d, answer is %d.\n", result, answer);
	}

	public static TreeNode arrayToTree(Integer[] array, int idx) {
		if (idx > (array.length - 1) || array[idx] == null) {
			return null;
		}
		TreeNode treeNode = new TreeNode(array[idx]);
		int leftIdx = ((idx+1)<<1) -1;
		int rightIdx = (idx+1)<<1;
		treeNode.left = arrayToTree(array, leftIdx);
		treeNode.right = arrayToTree(array, rightIdx);
		return treeNode;
	}

}
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		String s = "" + this.val + "\n";
		List<TreeNode> parents = Lists.newArrayList(this);
		List<TreeNode> children = Lists.newArrayList();

		while(!parents.isEmpty()) {
			String ps = "";
			for (TreeNode p: parents) {
				String leftVal = "null";
				String rightVal = "null";
				if (p.left != null) {
					children.add(p.left);
					leftVal = String.valueOf(p.left.val);
				}
				if (p.right != null) {
					children.add(p.right);
					rightVal = String.valueOf(p.right.val);
				}
				ps += (leftVal + " " + rightVal + " ");
			}
			ps += "\n";
			if (!children.isEmpty()) {
				s += ps;
			}
			parents = children;
			children = Lists.newArrayList();
		}
		return s;
	}
}