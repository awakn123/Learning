package leetcode.util;

import leetcode.ListNode;

/**
 * Created by 曹云 on 2020/8/11.
 */
public class ListNodeUtil {
	public static ListNode createListNode(int[] vals) {
		if (vals.length == 0) return null;
		ListNode root = new ListNode(vals[0]);
		ListNode parent = root;
		for (int i=1;i<vals.length;i++) {
			ListNode node = new ListNode(vals[i]);
			parent.next = node;
			parent = node;
		}
		return root;
	}

	public static ListNode getListNodeByIdx(ListNode root, int idx) {
		ListNode cur = root;
		while (idx>0) {
			if (cur.next == null)
				return cur;
			cur = cur.next;
			idx--;
		}
		return cur;
	}

	public static void connectToCycle(ListNode head, int idx) {
		ListNode cycleNode = null, cur = head, tail = null;
		while(cur != null) {
			if (idx == 0) {
				cycleNode = cur;
			} else {
				idx--;
			}
			tail = cur;
			cur = cur.next;
		}
		tail.next = cycleNode;
	}
}
