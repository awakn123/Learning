package leetcode.medium.linkedlist;

import leetcode.ListNode;
import leetcode.util.ListNodeUtil;
import leetcode.util.ResultCheck;

import java.util.*;

/**
 * Created by 曹云 on 2020/8/18.
 * 160. 相交链表 Intersection of Two Linked Lists.
 */
public class IntersectionTwoLinkedList {

	public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
		ListNode a = headA, b;
		while(a != null) {
			b = headB;
			while(b != null) {
				if (a == b)
					return a;
				else
					b = b.next;
			}
			a = a.next;
		}
		return null;
	}

	public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
		ListNode a = headA, b = headB;
		Set<ListNode> aset = new HashSet<>();
		while (a != null) {
			aset.add(a);
			a = a.next;
		}
		while(b != null) {
			if (aset.contains(b))
				return b;
			b = b.next;
		}
		return null;
	}
	public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
		ListNode a = headA, b = headB;
		boolean passA = false, passB = false;
		while (a!=null && b != null && a != b) {
			if (!passA && a.next == null) {
				a = headB;
				passA = true;
			} else {
				a = a.next;
			}
			if (!passB && b.next == null) {
				b = headA;
				passB = true;
			} else {
				b = b.next;
			}
		}
		return a == b? a : null;
	}

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) return null;
		ListNode pA = headA, pB = headB;
		while (pA != pB) {
			pA = pA == null ? headB : pA.next;
			pB = pB == null ? headA : pB.next;
		}
		return pA;
	}

	public static void main(String[] args){
		IntersectionTwoLinkedList main = new IntersectionTwoLinkedList();
		ListNode headAI = ListNodeUtil.createListNode(new int[]{4,1});
		ListNode headBI = ListNodeUtil.createListNode(new int[]{5,0,1});
		ListNode answerI = ListNodeUtil.createListNode(new int[]{8,4,5});
		headAI.next.next = answerI;
		headBI.next.next.next = answerI;
		ResultCheck.check(main.getIntersectionNode(headAI, headBI), answerI);
		ListNode headAII = ListNodeUtil.createListNode(new int[]{0,9,1,});
		ListNode headBII = ListNodeUtil.createListNode(new int[]{3,});
		ListNode answerII = ListNodeUtil.createListNode(new int[]{2,4});
		headAII.next.next.next = answerII;
		headBII.next = answerII;
		ResultCheck.check(main.getIntersectionNode(headAII, headBII), answerII);
		ListNode headAIII = ListNodeUtil.createListNode(new int[]{2,6,4});
		ListNode headBIII = ListNodeUtil.createListNode(new int[]{1,5});
		ResultCheck.check(main.getIntersectionNode(headAIII, headBIII), null);
		ListNode headBIV = ListNodeUtil.createListNode(new int[]{4,1});
		ListNode answerIV = ListNodeUtil.createListNode(new int[]{8,4,5});
		headBIV.next.next = answerIV;
		ResultCheck.check(main.getIntersectionNode(answerIV, headBIV), answerIV);
	}
}
