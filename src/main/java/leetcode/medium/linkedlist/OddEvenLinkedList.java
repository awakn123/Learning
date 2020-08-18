package leetcode.medium.linkedlist;

import leetcode.ListNode;
import leetcode.util.ListNodeUtil;
import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/18.
 * 328. Odd even linked list.
 * https://leetcode-cn.com/problems/odd-even-linked-list/solution/
 */
public class OddEvenLinkedList {
	public ListNode oddEvenList(ListNode head) {
		return null;
	}

	public static void main(String[] args){
		OddEvenLinkedList main = new OddEvenLinkedList();
		ListNode headI = ListNodeUtil.createListNode(new int[]{1,2,3,4,5});
		ListNode answerI = ListNodeUtil.createListNode(new int[]{1,3,5,2,4});
		ResultCheck.check(main.oddEvenList(headI), answerI);
		ListNode headII = ListNodeUtil.createListNode(new int[]{2,1,3,5,6,4,7});
		ListNode answerII = ListNodeUtil.createListNode(new int[]{2,3,6,7,1,5,4});
		ResultCheck.check(main.oddEvenList(headII), answerII);
	}
}
