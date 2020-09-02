package leetcode;

import java.util.Arrays;

public class ListNode {
	public int val;
	public ListNode next;

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
		String str = String.valueOf(val);
		ListNode fast = this.next;
		ListNode slow = this;
		while (fast != null && fast.next != null && fast != slow) {
			fast = fast.next.next;
			slow = slow.next;
			str += "," + slow.val;
		}
		if (fast == slow) {
			str += ", circle...";
		} else {
			while(slow.next != null) {
				slow = slow.next;
				str += "," + slow.val;
			}
		}
		return str;
	}
}
