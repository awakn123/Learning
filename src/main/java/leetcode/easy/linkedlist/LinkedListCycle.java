package leetcode.easy.linkedlist;

import leetcode.ListNode;
import leetcode.util.ListNodeUtil;
import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/11.
 * 141. Linked List Cycle
 * https://leetcode.com/problems/linked-list-cycle/
 */
public class LinkedListCycle {
	public boolean hasCycle(ListNode head) {
		return false;
	}

	public static void main(String[] args){
		LinkedListCycle main = new LinkedListCycle();
		ListNode headI = ListNodeUtil.createListNode(new int[]{3,2,0,-4});
		ListNodeUtil.connectToCycle(headI, 1);
		boolean answerI = true;
		boolean resultI = main.hasCycle(headI);
		ResultCheck.check(resultI, answerI);
		ListNode headII = ListNodeUtil.createListNode(new int[]{1, 2});
		ListNodeUtil.connectToCycle(headII, 0);
		boolean answerII = true;
		boolean resultII = main.hasCycle(headII);
		ResultCheck.check(resultII, answerII);
		ListNode headIII = ListNodeUtil.createListNode(new int[]{1});
		boolean answerIII = false;
		boolean resultIII = main.hasCycle(headII);
		ResultCheck.check(resultIII, answerIII);
	}
}
