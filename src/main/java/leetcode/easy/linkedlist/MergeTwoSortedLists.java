package leetcode.easy.linkedlist;

import leetcode.ListNode;
import leetcode.util.ListNodeUtil;
import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/11.
 * 21. Merge Two Sorted Lists
 * https://leetcode.com/problems/merge-two-sorted-lists/
 */
public class MergeTwoSortedLists {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		return null;
	}

	public static void main(String[] args){
		MergeTwoSortedLists main = new MergeTwoSortedLists();
		ListNode l1I = ListNodeUtil.createListNode(new int[]{1,2,4});
		ListNode l2I = ListNodeUtil.createListNode(new int[]{1,3,4});
		ListNode resultI = main.mergeTwoLists(l1I, l2I);
		ListNode answerI = ListNodeUtil.createListNode(new int[]{1,1,2,3,4,4});
		ResultCheck.check(resultI, answerI);
	}
}
