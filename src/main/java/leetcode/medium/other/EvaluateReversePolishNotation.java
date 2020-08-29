package leetcode.medium.other;

import leetcode.util.ResultCheck;

import java.util.Stack;

/**
 * Created by 曹云 on 2020/8/29.
 * 150. 逆波兰表达式求值
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/solution/
 */
public class EvaluateReversePolishNotation {

	public int evalRPN(String[] tokens) {
		Stack<Integer> stack = new Stack<>();
		for (String s: tokens) {
			int num;
			switch (s) {
				case "+":
					num = stack.pop() + stack.pop();
					break;
				case "-":
					int first = stack.pop();
					int second = stack.pop();
					num = second - first;
					break;
				case "*":
					num = stack.pop() * stack.pop();
					break;
				case "/":
					int divisor = stack.pop();
					int dividend = stack.pop();
					num = dividend / divisor;
					break;
				default:
					num = Integer.parseInt(s);
					break;
			}
			stack.push(num);
		}
		return stack.pop();
	}

	public static void main(String[] args){
		EvaluateReversePolishNotation main = new EvaluateReversePolishNotation();
		ResultCheck.check(main.evalRPN(new String[]{"2", "1", "+", "3", "*"}), 9);
		ResultCheck.check(main.evalRPN(new String[]{"4", "13", "5", "/", "+"}), 6);
		ResultCheck.check(main.evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}), 22);
		ResultCheck.check(main.evalRPN(new String[]{"4", "3", "-"}), 1);
	}
}
