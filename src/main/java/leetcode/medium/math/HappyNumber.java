package leetcode.medium.math;

import leetcode.util.ResultCheck;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 曹云 on 2020/8/28.
 * 202. 快乐数
 * https://leetcode-cn.com/problems/happy-number/solution/
 */
public class HappyNumber {
	public boolean isHappy1(int n) {
		Set<Integer> set = new HashSet<>();
		while (n != 1 && !set.contains(n)) {
			set.add(n);
			n = getNext(n);
		}
		return n == 1;
	}

	private int getNext(int n) {
		int num = 0;
		while (n != 0) {
			int mod = n%10;
			num += mod * mod;
			n /= 10;
		}
		return num;
	}

	public boolean isHappy(int n) {
		int fast = n, slow = n;
		do {
			fast = getNext(getNext(fast));
			slow = getNext(slow);
		} while (fast != slow && fast != 1);
		return fast == 1;
	}

	public static void main(String[] args){
		HappyNumber main = new HappyNumber();
		ResultCheck.check(main.isHappy(19), true);
	}
}
