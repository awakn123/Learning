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
		return null;
	}

	public static void main(String[] args){
		RemoveNthFromEnd main = new RemoveNthFromEnd();
		ListNode rootI = ListNodeUtil.createListNode(new int[]{1,2,3,4,5});
		ListNode resultI = main.removeNthFromEnd(rootI, 2);
		ListNode answerI = ListNodeUtil.createListNode(new int[]{1,2,3,5});
		ResultCheck.check(resultI, answerI);
	}
}
