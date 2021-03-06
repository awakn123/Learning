package leetcode.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import leetcode.ListNode;
import leetcode.easy.tree.MaxDepthBinaryTree;
import leetcode.easy.tree.ValidBST;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 曹云 on 2020/8/10.
 */
public class ResultCheck {
	private static void error(Object result, Object answer) {
		System.out.printf("Wrong, result is %s, answer is %s.\n", String.valueOf(result), String.valueOf(answer));
	}
	private static void listError(Object result, Object answer, int... idxs) {
		String idxstr = Arrays.stream(idxs).mapToObj(Integer::toString).collect(Collectors.joining("."));
		System.out.printf("the error index is %s, the result[%s] is %s, the answer[%s] is %s.\n", idxstr, idxstr, String.valueOf(result), idxstr, String.valueOf(answer));
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

	public static <T extends Object> void checkTwoDimension(List<List<T>> result, List<List<T>> answer) {
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
			error(result, answer);
			return;
		}

		for (int i=0; i<result.size(); i++) {
			if (result.get(i) == null || answer.get(i) == null) {
				error(result, answer);
				listError(result.get(i), answer.get(i), i);
				return;
			}
			if (result.get(i).size() != answer.get(i).size()) {
				error(result, answer);
				listError(result.get(i).size(), answer.get(i).size(), i);
				return;
			}
			for (int j=0;j<result.get(i).size();j++) {
				if (!result.get(i).get(j).equals(answer.get(i).get(j))) {
					error(result, answer);
					listError(result.get(i).get(j), answer.get(i).get(j), i, j);
					return;
				}
			}
		}
		pass();
	}

	public static void checkAVL(TreeNode root) {
		if (root == null) {
			return;
		}
		MaxDepthBinaryTree maxDepth = new MaxDepthBinaryTree();
		int leftDepth = maxDepth.maxDepth(root.left);
		int rightDepth = maxDepth.maxDepth(root.right);
		if (Math.abs(leftDepth - rightDepth) >1) {
			error(root, null);
		} else {
			checkAVL(root.left);
			checkAVL(root.right);
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
				error(Arrays.toString(result), Arrays.toString(answer));
				listError(result[i], answer[i], i);
				return;
			}
		}
		pass();
	}

	public static void checkBST(TreeNode root) {
		ValidBST validBST = new ValidBST();
		if (!validBST.isValidBST(root)) {
			error(root, null);
		} else {
			pass();
		}
	}

	public static <T extends Object> void checkList(List<T> result, List<T> answer) {
		if (result == answer) {
			pass();
			return;
		}
		if (result == null || answer == null) {
			error(result, answer);
			return;
		}
		if (result.size() != answer.size()) {
			System.out.println("error size.");
			error(result.size(), answer.size());
			error(result, answer);
			return;
		}
		for (int i=0; i<result.size(); i++) {
			if (result.get(i) != answer.get(i) &&
					(result.get(i) != null && !result.get(i).equals(answer.get(i)))) {
				error(result, answer);
				listError(result.get(i), answer.get(i), i);
				return;
			}
		}
		pass();
	}

	public static <T extends Object> void checkList(List<T> result, Set<T> answer) {
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
			error(result, answer);
			return;
		}
		for (int i=0; i<result.size(); i++) {
			if (result.get(i) != null && !answer.contains(result.get(i))) {
				error(result, answer);
				listError(result.get(i), null, i);
				return;
			}
		}
		pass();
	}

	public static void checkTwoDimension(int[][] result, int[][] answer) {
		if (result == answer) {
			pass();
			return;
		}
		if (result == null || answer == null) {
			error(result, answer);
			return;
		}
		List<List<Integer>> resultL = Lists.newArrayList();
		for (int i=0; i<result.length; i++) {
			resultL.add(Lists.newArrayList(Arrays.stream(result[i]).boxed().toArray(Integer[]::new)));
		}
		List<List<Integer>> answerL = Lists.newArrayList();
		for (int i=0; i<answer.length; i++) {
			answerL.add(Lists.newArrayList(Arrays.stream(answer[i]).boxed().toArray(Integer[]::new)));
		}
		checkTwoDimension(resultL, answerL);
	}

	public static void check(TreeNode result, TreeNode answer) {
		Queue<TreeNode> resultQueue = Queues.newArrayDeque();
		resultQueue.offer(result);
		Queue<TreeNode> answerQueue = Queues.newArrayDeque();
		answerQueue.offer(answer);
		int size = 1, idx = 0;
		TreeNode errResult = null, errAnswer = null;
		while(!resultQueue.isEmpty()) {
			if (size == 0) {
				idx++;
				size = resultQueue.size();
			}
			TreeNode r = resultQueue.poll(), a = answerQueue.poll();
			if (r == a || (r != null && a != null && r.val == a.val)) {
				size--;
				if (r.left != null) {
					resultQueue.offer(r.left);
				}
				if (r.right != null) {
					resultQueue.offer(r.right);
				}
				if (a.left != null) {
					answerQueue.offer(a.left);
				}
				if (a.right != null) {
					answerQueue.offer(a.right);
				}
			} else {
				errResult = r;
				errAnswer = a;
				break;
			}
		}
		if (errResult == errAnswer) {
			pass();
		} else {
			System.out.printf("error idx: %d.\n", idx);
			error(errResult, errAnswer);
			error(result, answer);
		}
	}

	public static void check(double result, double answer) {
		if (result == answer) {
			pass();
		} else {
			error(result, answer);
		}
	}
}
