package leetcode.easy.linkedlist;

import leetcode.ListNode;
import leetcode.util.ListNodeUtil;
import leetcode.util.ResultCheck;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 曹云 on 2020/8/11.
 * 141. Linked List Cycle
 * https://leetcode.com/problems/linked-list-cycle/
 */
public class LinkedListCycle {
	public boolean hasCycle(ListNode head) {
		Set<Integer> vals = new HashSet();
		while (head != null) {
			if (vals.contains(head.val))
				return true;
			vals.add(head.val);
			head = head.next;
		}
		return false;
	}
	public boolean hasCycle2(ListNode head) {
		if (head == null || head.next == null) return false;
		ListNode fast = head.next, slow = head;
		while (slow != fast) {
			if (fast == null || fast.next == null) return false;
			fast = fast.next.next;
			slow = slow.next;
		}
		return true;
	}

	public static void main(String[] args){
		LinkedListCycle main = new LinkedListCycle();
		ListNode headI = ListNodeUtil.createListNode(new int[]{3,2,0,-4});
		ListNodeUtil.connectToCycle(headI, 1);
		boolean answerI = true;
		boolean resultI = main.hasCycle2(headI);
		ResultCheck.check(resultI, answerI);
		ListNode headII = ListNodeUtil.createListNode(new int[]{1, 2});
		ListNodeUtil.connectToCycle(headII, 0);
		boolean answerII = true;
		boolean resultII = main.hasCycle2(headII);
		ResultCheck.check(resultII, answerII);
		ListNode headIII = ListNodeUtil.createListNode(new int[]{1});
		boolean answerIII = false;
		boolean resultIII = main.hasCycle2(headIII);
		ResultCheck.check(resultIII, answerIII);
	}
}
