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
		if (lists.length == 0) return null;
		return mergeKLists(lists, 0, lists.length - 1);
	}

	private ListNode mergeKLists(ListNode[] lists, int left, int right) {
		if (left == right)
			return lists[left];
		int mid = (left + right)/2;
		ListNode leftNode = this.mergeKLists(lists, left, mid);
		ListNode rightNode = this.mergeKLists(lists, mid + 1, right);
		ListNode dummy = new ListNode(), node = dummy;
		while(leftNode != null && rightNode != null) {
			if (leftNode.val < rightNode.val) {
				node.next = leftNode;
				leftNode = leftNode.next;
				node = node.next;
			} else {
				node.next = rightNode;
				rightNode = rightNode.next;
				node = node.next;
			}
		}
		if(leftNode != null)
			node.next = leftNode;
		if(rightNode != null)
			node.next = rightNode;
		return dummy.next;
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
