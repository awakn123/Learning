package leetcode.hard.design;

import leetcode.util.ResultCheck;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 曹云 on 2020/11/2.
 * https://leetcode-cn.com/problems/lru-cache/solution/
 */
public class LRUCache {
	private class ListNode {
		int key;
		int value;
		ListNode next;
		ListNode prev;
		public ListNode(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	Map<Integer, ListNode> map = new HashMap<>();
	ListNode head;
	ListNode tail;
	int capacity;
	public LRUCache(int capacity) {
		this.capacity = capacity;
		head = new ListNode(0, 0);
		tail = new ListNode(0, 0);
		head.next = tail;
		tail.prev = head;
	}

	private void moveNodeToHead(ListNode node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		node.next = head.next;
		node.next.prev = node;
		node.prev = head;
		head.next = node;
	}

	public int get(int key) {
		ListNode node = map.get(key);
		if (node == null)
			return -1;
		moveNodeToHead(node);
		return node.value;
	}

	public void put(int key, int value) {
		ListNode node = map.get(key);
		if (node != null) {
			moveNodeToHead(node);
			node.value = value;
		} else {
			node = new ListNode(key, value);
			map.put(key, node);
			node.next = head.next;
			node.prev = head;
			head.next = node;
			node.next.prev = node;
			if (map.size() > capacity) {
				node = tail.prev;
				tail.prev = node.prev;
				node.prev.next = tail;
				map.remove(node.key);
			}
		}
	}

	public static void main(String[] args){
		LRUCache main = new LRUCache(2);
		ResultCheck.check(main.get(1), -1);
		main.put(1, 1);
		ResultCheck.check(main.get(1), 1);
		main.put(2, 2);
		main.put(4, 4);
		ResultCheck.check(main.get(1), -1);
		ResultCheck.check(main.get(2), 2);
		ResultCheck.check(main.get(4), 4);
	}
}
