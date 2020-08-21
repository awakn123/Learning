package leetcode.medium.treeandgraph;

import leetcode.util.Node;

/**
 * Created by 曹云 on 2020/8/18.
 * 116. 填充每个节点的下一个右侧节点指针 Populating next right pointers in each node.
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/solution/
 */
public class PopulatingNextRightPointers {

	public Node connect(Node root) {
		return null;
	}

	public static void main(String[] args){
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		PopulatingNextRightPointers main = new PopulatingNextRightPointers();
		main.connect(root);
		if (root.next != null) {
			System.out.println("root.next is not null.");
		} else if (root.left.next != root.right) {
			System.out.println("left.next is not right.");
		} else if (root.right.next != null) {
			System.out.println("right.next is not null.");
		}
	}
}
