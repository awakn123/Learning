package leetcode.easy.linkedlist;

import leetcode.ListNode;
import leetcode.util.ListNodeUtil;
import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/11.
 * 234. Palindrome Linked List
 * https://leetcode.com/problems/palindrome-linked-list/
 */
public class PalindromeLinkedList {
	public boolean isPalindrome(ListNode head) {
		return false;
	}

	public static void main(String[] args){
		PalindromeLinkedList main = new PalindromeLinkedList();
		ListNode headI = ListNodeUtil.createListNode(new int[]{1,2});
		boolean resultI = main.isPalindrome(headI);
		boolean answerI = false;
		ResultCheck.check(resultI, answerI);
		ListNode headII = ListNodeUtil.createListNode(new int[]{1,2,2,1});
		boolean resultII = main.isPalindrome(headI);
		boolean answerII = true;
		ResultCheck.check(resultII, answerII);
	}
}
