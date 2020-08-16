package leetcode.easy.devise;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/16.
 */
public class MinStack {

	public MinStack() {

	}

	public void push(int x) {

	}

	public void pop() {

	}

	public int top() {
		return 0;
	}

	public int getMin() {
		return 0;
	}

	public static void main(String[] args){

		MinStack minStack = new MinStack();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		ResultCheck.check(minStack.getMin(), -3);// --> 返回 -3.
		minStack.pop();
		ResultCheck.check(minStack.top(), 0);// --> 返回 0.
		ResultCheck.check(minStack.getMin(), -2);//   --> 返回 -2.

	}
}
