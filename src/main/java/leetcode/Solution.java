package leetcode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
	public static ListNode reverseList(ListNode head) {
		ListNode tmp = head;
		ListNode result = null;
		while(tmp != null) {
			ListNode tmp2 = result;
			ListNode tmp3 = tmp;
			tmp = tmp.next;
			result = tmp3;
			result.next = tmp2;
		}
		// my poor solution is slow at the new, the new consume;
		// I try to make a new chain, and make every next to be previous result.That's ok,but poor.
		// I should try to make result as head. result = tmp & result.next = tmp2 will make tmp lose its next value.
		// So I should make a new pointer.
		// yes, try it.
		return result;
	}
	public static ListNode reverseList2(ListNode head) {
		ListNode newHead = null;
		while (head != null) {
			ListNode next = head.next;
			head.next = newHead;
			newHead = head;
			head = next;
		}
		return newHead;
	}

	public static ListNode reverseList3(ListNode head) {
		if(head == null || head.next == null) return head;
		ListNode tmp, pre;
		tmp = head.next;
		pre = head;
		// pre is always head，but pre's next is changing to next.next in loop.
		while(tmp!=null){
			pre.next = tmp.next;// changing to next.next;
			tmp.next = head;//current head.next = previous;
			head = tmp;//head = current head;
			tmp = pre.next;//changing to next;
		}
		// last: why the head could be null?
		// the block has three pointers,but heap is one;
		// can imagine a chain, just split the head's next,move to head, and go on;
		return head;//result
	}
	public static void main(String[] args) {
	    ListNode head = new ListNode(1);
		ListNode next = new ListNode(2);
		head.next = next;
		head.next.next = new ListNode(3);
		System.out.println(head);
		System.out.println(reverseList(head));
	}
}