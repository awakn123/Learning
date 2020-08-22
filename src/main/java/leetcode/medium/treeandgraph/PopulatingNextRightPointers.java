package leetcode.medium.treeandgraph;

import leetcode.util.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by 曹云 on 2020/8/18.
 * 116. 填充每个节点的下一个右侧节点指针 Populating next right pointers in each node.
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/solution/
 */
public class PopulatingNextRightPointers {

	public Node connect1(Node root) {
		if (root == null) return root;
		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		int size = 1;
		Node next = null;
		while (!queue.isEmpty()) {
			if (size == 0) {
				size = queue.size();
				next = null;
			}
			size--;
			Node node = queue.poll();
			node.next = next;
			next = node;
			if (node.right != null) {
				queue.offer(node.right);
				queue.offer(node.left);
			}
		}
		return root;
	}

	public Node connect(Node root) {
		if (root == null) return root;
		Node left = root;
		while (left.left != null) {
			Node node = left;
			while (node != null) {
				node.left.next = node.right;
				node.right.next = node.next == null ? null : node.next.left;
				node = node.next;
			}
			left = left.left;
		}
		return root;
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
		} else {
			System.out.println("pass.");
		}
	}
}
