package leetcode.hard.linkedlist;

import leetcode.ListNode;
import leetcode.util.ListNodeUtil;
import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/30.
 * 148. 排序链表
 * https://leetcode-cn.com/problems/sort-list/solution/
 */
public class SortList {
	public ListNode sortList1(ListNode head) {

		if (head == null || head.next == null) return head;
		ListNode left = head.next, right = head.next, leftHead = null, leftParent = head, rightParent = head;
		while(right != null) {
			if (right.val >= head.val) {
				rightParent = right;
				right = right.next;
			} else {
				if (right != left) {
					ListNode tmp = right.next;
					leftParent.next = right;
					rightParent.next = left;
					leftParent.next.next = left.next != right ? left.next : left;
					rightParent.next.next = tmp;
					left = rightParent = right;
					right = rightParent.next;

				}
				if (leftHead == null) {
					leftHead = left;
				}
				leftParent = left;
				left = left.next;
				rightParent = right;
				right = right.next;
			}
		}
		leftParent.next = null;
		ListNode leftSort = sortList1(leftHead);
		ListNode rightSort = sortList1(left);
		if (leftSort != null) {
			ListNode node = leftSort;
			while(node.next != null) {
				node = node.next;
			}
			node.next = head;
		} else {
			leftSort = head;
		}
		head.next = rightSort;
		return leftSort;
	}

	public ListNode sortList2(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode fast = head.next.next, slow = head.next, slowParent = head;
		while(fast != null && fast.next != null) {
			fast = fast.next.next;
			slowParent = slow;
			slow = slow.next;
		}
		slowParent.next = null;
		ListNode left = sortList2(head), right = sortList2(slow);
		ListNode dummy = new ListNode(), node = dummy;
		while(left != null && right != null) {
			if (left.val < right.val) {
				node.next = left;
				left = left.next;
			} else {
				node.next = right;
				right = right.next;
			}
			node = node.next;
		}
		node.next = left != null ? left : right;
		return dummy.next;
	}

	public ListNode sortList(ListNode head) {
		ListNode dummy = new ListNode(), node = head, left, right, leftHead, rightHead, start, result;
		int len = 0, inv = 1;
		while(node != null) {
			node = node.next;
			len++;
		}
		dummy.next = head;
		// 按1->2->4->8的顺序，两两进行归并。
		while (inv < len) {
			start = dummy.next;
			result = dummy;
			// 记录每次子循环的开始节点。
			while (start != null) {
				int i=1;
				// 生成用于归并的左节点
				left = leftHead = start;
				while (i<inv && left != null) {
					i++;
					left = left.next;
				}
				if (left == null || left.next == null) {
					// 左节点直接结束了，则将左节点并入结果中。
					result.next = leftHead;
					break;
				}
				// 生成用于归并的右节点。
				rightHead = right = left.next;
				left.next = null;
				i = 1;
				while (i<inv && right != null) {
					i++;
					right = right.next;
				}
				if (right == null) {
					start = null;
				} else {
					start = right.next;
					right.next = null;
				}

				// 合并左右节点
				left = leftHead;
				right = rightHead;
				while(left != null && right != null) {
					if (left.val < right.val) {
						result.next = left;
						left = left.next;
					} else {
						result.next = right;
						right = right.next;
					}
					result = result.next;
				}
				result.next = left != null ? left : right;
				// 为下一组的合并准备存储位置。
				while(result.next != null) {
					result = result.next;
				}
			}
			inv *= 2;
		}
		return dummy.next;
	}

	public static void main(String[] args){
		SortList main = new SortList();
		ResultCheck.check(main.sortList(ListNodeUtil.createListNode(new int[]{3,4,0})), ListNodeUtil.createListNode(new int[]{0,3,4}));
		ResultCheck.check(main.sortList(ListNodeUtil.createListNode(new int[]{4,2,1,3})), ListNodeUtil.createListNode(new int[]{1,2,3,4}));
		ResultCheck.check(main.sortList(ListNodeUtil.createListNode(new int[]{-1,5,3,4,0})), ListNodeUtil.createListNode(new int[]{-1,0,3,4,5}));
	}
}
