package leetcode.medium.linkedlist;

import leetcode.ListNode;
import leetcode.util.ListNodeUtil;
import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/18.
 * 160. 相交链表 Intersection of Two Linked Lists.
 */
public class IntersectionTwoLinkedList {

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		return null;
	}

	public static void main(String[] args){
		IntersectionTwoLinkedList main = new IntersectionTwoLinkedList();
		ListNode headAI = ListNodeUtil.createListNode(new int[]{4,1,8,4,5});
		ListNode headBI = ListNodeUtil.createListNode(new int[]{5,0,1,8,4,5});
		ListNode answerI = ListNodeUtil.createListNode(new int[]{8,4,5});
		ResultCheck.check(main.getIntersectionNode(headAI, headBI), answerI);
		ListNode headAII = ListNodeUtil.createListNode(new int[]{0,9,1,2,4});
		ListNode headBII = ListNodeUtil.createListNode(new int[]{3,2,4});
		ListNode answerII = ListNodeUtil.createListNode(new int[]{2,4});
		ResultCheck.check(main.getIntersectionNode(headAII, headBII), answerII);
		ListNode headAIII = ListNodeUtil.createListNode(new int[]{2,6,4});
		ListNode headBIII = ListNodeUtil.createListNode(new int[]{1,5});
		ResultCheck.check(main.getIntersectionNode(headAIII, headBIII), null);
	}
}
