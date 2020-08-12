package leetcode.easy.linkedlist;

import leetcode.ListNode;
import leetcode.util.ListNodeUtil;
import leetcode.util.ResultCheck;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 曹云 on 2020/8/11.
 * 234. Palindrome Linked List
 * https://leetcode.com/problems/palindrome-linked-list/
 */
public class PalindromeLinkedList {
	/**
	 * two pointers.
	 * @param head
	 * @return
	 */
	public boolean isPalindrome(ListNode head) {
		List<Integer> list = new ArrayList();
		while (head != null) {
			list.add(head.val);
			head = head.next;
		}
		int left = 0, right = list.size() - 1;
		while (left < right) {
			if (list.get(left) != list.get(right))
				return false;
			left++;
			right--;
		}
		return true;
	}

	/**
	 * recurse
	 * @param head
	 * @return
	 */
	public boolean isPalindrome2(ListNode head) {
		if (head == null) return true;
		return recurse(head, head) != null;
	}

	private ListNode recurse(ListNode node, ListNode head){
		if (node.next != null)
			head = recurse(node.next, head);
		if (head == null || node.val != head.val) return null;
		return head.next == null ? head : head.next;
	}

	/**
	 * O(1) extra space.
	 * use many loop to reduce extra space. time to space.
	 * @param head
	 * @return
	 */
	public boolean isPalindrome3(ListNode head) {
//		if (head == null) return true;
		ListNode halfNode = this.getHalfList(head);
		ListNode rehalfNode = this.reverseListNode(halfNode);
		ListNode re = rehalfNode, h = head;
		while (re != null) {
			if (re.val != h.val)
				return false;
			re = re.next;
			h = h.next;
		}
		this.reverseListNode(rehalfNode);
		return true;
	}

	private ListNode getHalfList(ListNode head) {
		ListNode slow = head, fast = head;
		while(fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
	private ListNode reverseListNode(ListNode head) {
		ListNode result = null;
		while (head != null) {
			ListNode tmp = head;
			head = head.next;
			tmp.next = result;
			result = tmp;
		}
		return result;
	}


	public static void main(String[] args){
		PalindromeLinkedList main = new PalindromeLinkedList();
		ListNode headI = ListNodeUtil.createListNode(new int[]{1,2});
		boolean resultI = main.isPalindrome3(headI);
		boolean answerI = false;
		ResultCheck.check(resultI, answerI);
		ListNode headII = ListNodeUtil.createListNode(new int[]{1,2,2,1});
		boolean resultII = main.isPalindrome3(headII);
		boolean answerII = true;
		ResultCheck.check(resultII, answerII);
	}
}
