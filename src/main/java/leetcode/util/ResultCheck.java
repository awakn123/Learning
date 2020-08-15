package leetcode.util;

import leetcode.ListNode;
import leetcode.easy.tree.MaxDepthBinaryTree;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 曹云 on 2020/8/10.
 */
public class ResultCheck {
	private static void error(Object result, Object answer) {
		System.out.printf("Wrong, result is %s, answer is %s.\n", String.valueOf(result), String.valueOf(answer));
	}
	private static void listError(Object result, Object answer, int... idxs) {
		String idxstr = Arrays.stream(idxs).mapToObj(Integer::toString).collect(Collectors.joining(","));
		System.out.printf("the error index is %s, the result idx is %s, the answer idx is %s.\n", idxstr, String.valueOf(result), String.valueOf(answer));
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

	public static void check(List<List<Integer>> result, List<List<Integer>> answer) {
		if (result == answer) {
			pass();
			return;
		}
		if (result == null || answer == null) {
			error(result, answer);
			return;
		}
		if (result.size() != answer.size()) {
			error(result.size(), answer.size());
			return;
		}

		for (int i=0; i<result.size(); i++) {
			if (result.get(i) == null || answer.get(i) == null) {
				listError(result.get(i), answer.get(i), i);
				return;
			}
			if (result.get(i).size() != answer.get(i).size()) {
				listError(result.get(i).size(), answer.get(i).size(), i);
				return;
			}
			for (int j=0;j<result.get(i).size();j++) {
				if (result.get(i).get(j) != answer.get(i).get(j)) {
					listError(result.get(i).get(j), answer.get(i).get(j), i, j);
					return;
				}
			}
		}
		pass();
	}

	public static void checkAVL(TreeNode root) {
		if (root == null) {
			error(root, null);
			return;
		}
		MaxDepthBinaryTree maxDepth = new MaxDepthBinaryTree();
		int leftDepth = maxDepth.maxDepth(root.left);
		int rightDepth = maxDepth.maxDepth(root.right);
		if (Math.abs(leftDepth - rightDepth) <=1) {
			pass();
		} else {
			error(root, null);
		}
	}

	public static void check(int[] result, int[] answer) {
		if (result == answer) {
			pass();
			return;
		}
		if (result == null || answer == null || result.length != answer.length){
			error(result, answer);
			return;
		}
		for (int i=0; i<result.length; i++) {
			if (result[i] != answer[i]) {
				error(result, answer);
				listError(result[i], answer[i], i);
				return;
			}
		}
		pass();
	}
}
