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
		while(result != null && answer != null) {
			if (result.getVal() != answer.getVal()) {
				error(result, answer);
				return;
			}
			result = result.getNext();
			answer = result.getNext();
			idx++;
		}
		if (result == answer) {
			pass();
		} else {
			System.out.println("error idx:" + idx);
			error(result, answer);
		}
	}
}
