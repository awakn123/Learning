package leetcode.easy.linkedlist;

import leetcode.ListNode;
import leetcode.util.ListNodeUtil;
import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/11.
 * 19. Remove Nth Node From End of List
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthFromEnd {
	public ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null || n < 0) return head;
		ListNode cur = head, result = null, resultParent = null;
		int idx = 0;
		while (cur != null) {
			idx++;
			int resultIdx = idx - n;
			if (resultIdx == 0) {
				result = head;
			} else if (resultIdx > 0) {
				resultParent = result;
				result = result.next;
			}
			cur = cur.next;
		}
		if (resultParent != null) {
			resultParent.next = result.next;
		} else if (result.next != null){
			result.val = result.next.val;
			result.next = result.next.next;
		} else {
			return null;
		}
		return head;
	}

	/**
	 * see and reimplement.
	 * dummy is very crucial.
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode removeNthFromEnd2(ListNode head, int n) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode front = dummy, rear = dummy;
		for (int i=1; i<= n; i++) {
			front = front.next;
		}
		while (front.next != null) {
			front = front.next;
			rear = rear.next;
		}
		rear.next = rear.next.next;
		return dummy.next;
	}


	public static void main(String[] args){
		RemoveNthFromEnd main = new RemoveNthFromEnd();
		ListNode rootI = ListNodeUtil.createListNode(new int[]{1,2,3,4,5});
		ListNode resultI = main.removeNthFromEnd2(rootI, 2);
		ListNode answerI = ListNodeUtil.createListNode(new int[]{1,2,3,5});
		ResultCheck.check(resultI, answerI);
		ListNode rootII = ListNodeUtil.createListNode(new int[]{1});
		ListNode resultII = main.removeNthFromEnd2(rootII, 1);
		ListNode answerII = ListNodeUtil.createListNode(new int[]{});
		ResultCheck.check(resultII, answerII);
		ListNode rootIII = ListNodeUtil.createListNode(new int[]{1,2});
		ListNode resultIII = main.removeNthFromEnd2(rootIII, 2);
		ListNode answerIII = ListNodeUtil.createListNode(new int[]{2});
		ResultCheck.check(resultIII, answerIII);
	}
}
