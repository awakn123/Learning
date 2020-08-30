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
	public ListNode sortList(ListNode head) {
		return null;
	}

	public static void main(String[] args){
		SortList main = new SortList();
		ResultCheck.check(main.sortList(ListNodeUtil.createListNode(new int[]{4,2,1,3})), ListNodeUtil.createListNode(new int[]{1,2,3,4}));
		ResultCheck.check(main.sortList(ListNodeUtil.createListNode(new int[]{-1,5,3,4,0})), ListNodeUtil.createListNode(new int[]{-1,0,3,4,5}));
	}
}
