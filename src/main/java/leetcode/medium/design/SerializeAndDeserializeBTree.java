package leetcode.medium.design;

import leetcode.util.ResultCheck;
import leetcode.util.TreeNode;
import leetcode.util.TreeNodeUtil;

/**
 * Created by 曹云 on 2020/8/27.
 * 297. 二叉树的序列化与反序列化
 * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/solution/
 */
public class SerializeAndDeserializeBTree {
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		return null;
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		return null;
	}

	public static void main(String[] args){
		SerializeAndDeserializeBTree main = new SerializeAndDeserializeBTree();
		TreeNode node = TreeNodeUtil.createTreeNode(new Integer[]{1,2,3, null, null, 4, 5});
		String str = main.serialize(node);
		ResultCheck.check(str,"[1,2,3,null,null,4,5]");
		ResultCheck.check(main.deserialize(str), node);
	}
}
