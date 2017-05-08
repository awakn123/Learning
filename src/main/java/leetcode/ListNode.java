package leetcode;

public class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}

	@Override
	public String toString() {
		String nextStr = next == null ? "" : next.toString();
		return val + "," + nextStr;
	}
}
