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
			parent.setNext(node);
			parent = node;
		}
		return root;
	}

	public static ListNode getListNodeByIdx(ListNode root, int idx) {
		ListNode cur = root;
		while (idx>0) {
			if (cur.getNext() == null)
				return cur;
			cur = cur.getNext();
			idx--;
		}
		return cur;
	}
}
