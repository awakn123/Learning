package leetcode.hard.linkedlist;

import leetcode.ListNode;
import leetcode.util.ListNodeUtil;
import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/30.
 * 23. 合并K个升序链表
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/
 */
public class MergeKSortedList {
	public ListNode mergeKLists(ListNode[] lists) {
		return null;
	}

	public static void main(String[] args){
		MergeKSortedList main = new MergeKSortedList();
		ListNode[] lists = new ListNode[]{
				ListNodeUtil.createListNode(new int[]{1,4,5}),
				ListNodeUtil.createListNode(new int[]{1,3,4}),
				ListNodeUtil.createListNode(new int[]{2,6}),
		};
		ResultCheck.check(main.mergeKLists(lists), ListNodeUtil.createListNode(new int[]{1,1,2,3,4,4,5,6}));
	}

}
