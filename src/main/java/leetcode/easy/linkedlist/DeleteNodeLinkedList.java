package leetcode.easy.linkedlist;

import leetcode.ListNode;
import leetcode.util.ListNodeUtil;
import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/11.
 * 237. Delete Node in a Linked List
 * https://leetcode.com/problems/delete-node-in-a-linked-list/
 */
public class DeleteNodeLinkedList {
	public void deleteNode(ListNode node) {

	}

	public static void main(String[] args){
		DeleteNodeLinkedList main = new DeleteNodeLinkedList();
		ListNode rootI = ListNodeUtil.createListNode(new int[]{4,5,1,9});
		ListNode nodeI = ListNodeUtil.getListNodeByIdx(rootI, 1);
		main.deleteNode(nodeI);
		ListNode answerI = ListNodeUtil.createListNode(new int[]{4,1,9});
		ResultCheck.check(rootI, answerI);
		ListNode rootII = ListNodeUtil.createListNode(new int[]{4,5,1,9});
		ListNode nodeII = ListNodeUtil.getListNodeByIdx(rootI, 2);
		main.deleteNode(nodeII);
		ListNode answerII = ListNodeUtil.createListNode(new int[]{4,5,9});
		ResultCheck.check(rootII, answerII);
	}
}
