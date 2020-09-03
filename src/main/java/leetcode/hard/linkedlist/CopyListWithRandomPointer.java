package leetcode.hard.linkedlist;

import leetcode.util.Node;
import leetcode.util.ResultCheck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 曹云 on 2020/8/30.
 * 138. 复制带随机指针的链表
 * https://leetcode-cn.com/problems/copy-list-with-random-pointer/solution/
 */
public class CopyListWithRandomPointer {
	public Node copyRandomList1(Node head) {
		Map<Node, Node> map = new HashMap<>();
		Node node = head, dummy = new Node(-1), copyNode = dummy;
		while(node != null) {
			copyNode.next = new Node(node.val);
			copyNode = copyNode.next;
			map.put(node, copyNode);
			node = node.next;
		}

		node = head;
		copyNode = dummy.next;
		while(node != null) {
			if (node.random != null) {
				copyNode.random = map.get(node.random);
			}
			node = node.next;
			copyNode = copyNode.next;
		}

		return dummy.next;
	}
	public Node copyRandomList(Node head) {
		if (head == null) return null;
		Node node = head;
		while (node != null) {
			Node copyNode = new Node(node.val);
			copyNode.next = node.next;
			node.next = copyNode;
			node = copyNode.next;
		}
		node = head;
		while(node != null) {
			node.next.random = node.random == null ? null : node.random.next;
			node = node.next.next;
		}
		node = head;
		Node res = head.next, resNode = res;
		while(node != null) {
			node.next = node.next.next;
			resNode.next = resNode.next == null ? null : resNode.next.next;
			node = node.next;
			resNode = resNode.next;
		}
		return res;
	}
	public static void main(String[] args){
		CopyListWithRandomPointer main = new CopyListWithRandomPointer();
		Node head = new Node(7);
		head.next = new Node(13);
		head.next.next = new Node(11);
		main.copyRandomList(head);
	}
}
