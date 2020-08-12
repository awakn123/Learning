package leetcode.util;

import leetcode.ListNode;

/**
 * Created by 曹云 on 2020/8/10.
 */
public class ResultCheck {
	private static void error(Object result, Object answer) {
		System.out.printf("Wrong, result is %s, answers is %s.\n", String.valueOf(result), String.valueOf(answer));
	}
	private static void pass() {
		System.out.println("Check pass.");
	}
	public static void check(int result, int answer) {
		if (result == answer) {
			pass();
		} else {
			error(result, answer);
		}
	}

	public static void check(boolean result, boolean answer) {
		if (result == answer) {
			pass();
		} else {
			error(result, answer);
		}
	}

	public static void check(String result, String answer) {
		if (result == answer || (result != null && result.equals(answer))) {
			pass();
		} else {
			error(result, answer);
		}
	}

	public static void check(ListNode result, ListNode answer) {
		int idx = 0;
		ListNode curResult = result, curAnswer = answer;
		while(curResult != null && curAnswer != null) {
			if (curResult.val != curAnswer.val) {
				error(result, answer);
				return;
			}
			curResult = curResult.next;
			curAnswer = curAnswer.next;
			idx++;
		}
		if (curResult == curAnswer) {
			pass();
		} else {
			System.out.println("error idx:" + idx);
			error(curResult, curAnswer);
		}
	}
}
