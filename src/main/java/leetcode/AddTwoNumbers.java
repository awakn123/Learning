package leetcode;

public class AddTwoNumbers {
	public static void main(String[] args) {
	    ListNode node1 = new ListNode(1,8);
		ListNode node2 = new ListNode(0);
		ListNode result = addTwoNumbers(node1, node2);
		System.out.println("expect:1,8,actual:" + result);
	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode node;
		int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
		if (sum > 9) {
			node = new ListNode(sum%10);
			if (l1.next != null) {
				l1.next.val++;
				node.next = addTwoNumbers(l1.next, l2.next);
			} else if (l2.next != null) {
				l2.next.val++;
				node.next = addTwoNumbers(l1.next, l2.next);
			} else  {
				node.next = new ListNode(1);
			}
		} else {
			node = new ListNode(sum);
			if (l1.next != null || l2.next != null) {
				node.next = addTwoNumbers(l1.next, l2.next);
			}
		}
		return node;
	}
}
