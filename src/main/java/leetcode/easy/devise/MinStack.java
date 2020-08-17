package leetcode.easy.devise;

import leetcode.util.ResultCheck;

import java.util.Arrays;

/**
 * Created by 曹云 on 2020/8/16.
 */
public class MinStack {

	int[] contents;
	//Integer min;
	int[] mincontents;
	int length;

	public MinStack() {
		contents = new int[8];
//		min = null;
		mincontents = new int[8];
		length = 0;
	}

	public void push(int x) {
		if (this.length == this.contents.length) {
			this.contents = Arrays.copyOf(this.contents, this.length * 2);
			this.mincontents = Arrays.copyOf(this.mincontents, this.length * 2);
		}
		contents[this.length++] = x;
		if (this.length == 1)
			this.mincontents[this.length - 1] = x;
		else
			this.mincontents[this.length - 1] = Math.min(this.mincontents[this.length - 2] ,x);
	}

	public void pop() {
//		int x = contents[--this.length];
		this.length--;
		contents[this.length] = 0;
		mincontents[this.length] = 0;
//		if (this.min == x) {
//			if (this.length == 0)
//				this.min = null;
//			else {
//				min = contents[0];
//				for (int i=1;i<length;i++) {
//					min = Math.min(min, contents[i]);
//				}
//			}
//		}
	}

	public int top() {
		return contents[this.length - 1];
	}

	public int getMin() {
//		return min;
		return mincontents[this.length - 1];
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
