package leetcode.hard.other;

import leetcode.util.ResultCheck;

import java.util.Stack;

/**
 * Created by 曹云 on 2020/11/13.
 * 1118.Number of Days in a Month(没买会员暂时看不了）
 * https://labuladong.gitbook.io/algo/shu-ju-jie-gou-xi-lie/2.4-shou-ba-shou-she-ji-shu-ju-jie-gou/dan-tiao-zhan
 * 给你一个数组 T，这个数组存放的是近几天的天气气温，你返回一个等长的数组，计算：对于每一天，你还要至少等多少天才能等到一个更暖和的气温；如果等不到那一天，填 0。
 * 单调栈
 */
public class NumberofDaysInaMonth {
	private int[] dailyTemperatures(int[] temperatures) {
		int[] result = new int[temperatures.length];
		Stack<Integer> stack = new Stack<>();
		for (int i=temperatures.length -1; i>=0; i--) {
			while(!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
				stack.pop();
			}
			result[i] = stack.isEmpty() ? 0 : (stack.peek() - i);
			stack.push(i);
		}
		return result;
	}

	public static void main(String[] args){
		NumberofDaysInaMonth main = new NumberofDaysInaMonth();
		ResultCheck.check(main.dailyTemperatures(new int[]{73,74,75,71,69,76}), new int[]{1,1,3,2,1,0});
	}
}
