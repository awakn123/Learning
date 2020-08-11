package leetcode.easy.linkedlist;

import leetcode.ListNode;
import leetcode.util.ListNodeUtil;
import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/11.
 * 206. Reverse Linked List
 * https://leetcode.com/problems/reverse-linked-list/
 */
public class ReverseLinkedList {
	public ListNode reverseList(ListNode head) {
		return null;
	}

	public static void main(String[] args){
		ReverseLinkedList main = new ReverseLinkedList();
		ListNode headI = ListNodeUtil.createListNode(new int[]{1,2,3,4,5});
		ListNode answerI = ListNodeUtil.createListNode(new int[]{5,4,3,2,1});
		ListNode resultI = main.reverseList(headI);
		ResultCheck.check(resultI, answerI);
	}
}
