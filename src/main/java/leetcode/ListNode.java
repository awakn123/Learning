package leetcode;

import java.util.Arrays;

public class ListNode {
	int val;
	ListNode next;

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public ListNode getNext() {
		return next;
	}

	public void setNext(ListNode next) {
		this.next = next;
	}

	public ListNode() {}
	public ListNode(int x) {
		val = x;
	}
	ListNode(int... xlist) {
		if (xlist == null || xlist.length == 0)
			return;
		val = xlist[0];
		if (xlist.length > 1)
			next = new ListNode(Arrays.copyOfRange(xlist, 1, xlist.length));
	}
	ListNode(int val, ListNode next) { this.val = val; this.next = next; }

	@Override
	public String toString() {
		String nextStr = next == null ? "" : next.toString();
		return val + "," + nextStr;
	}
}
