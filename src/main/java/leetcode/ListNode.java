package leetcode;

import java.util.Arrays;

public class ListNode {
	int val;
	ListNode next;

	ListNode() {}
	ListNode(int x) {
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
