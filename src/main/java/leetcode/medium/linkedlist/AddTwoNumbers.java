package leetcode.medium.linkedlist;

import leetcode.ListNode;
import leetcode.util.ListNodeUtil;
import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/18.
 * 2. Add Two Numbers
 * https://leetcode-cn.com/problems/add-two-numbers/solution/
 */
public class AddTwoNumbers {

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(), node = dummy;
		int plus = 0;
		while(l1 != null || l2 != null) {
			int val = (l1 != null ? l1.val : 0)
					+ (l2 != null ? l2.val : 0)
					+ plus;
			plus = val/10;
			val %= 10;
			node.next = new ListNode(val);
			node = node.next;
			l1 = l1 == null ? null : l1.next;
			l2 = l2 == null ? null : l2.next;
		}
		if (plus > 0)
			node.next = new ListNode(plus);
		return dummy.next;
	}

	public static void main(String[] args){
		AddTwoNumbers main = new AddTwoNumbers();
		ListNode l1 = ListNodeUtil.createListNode(new int[]{2,4,3});
		ListNode l2 = ListNodeUtil.createListNode(new int[]{5,6,4});
		ListNode answer = ListNodeUtil.createListNode(new int[]{7,0,8});
		ResultCheck.check(main.addTwoNumbers(l1, l2), answer);
	}
}
