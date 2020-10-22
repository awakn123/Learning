package leetcode.hard.math;

import leetcode.util.ResultCheck;

import java.util.Arrays;

/**
 * Created by 曹云 on 2020/9/25.
 * 564. 寻找最近的回文数
 * https://leetcode-cn.com/problems/find-the-closest-palindrome/
 */
public class FindClosestPalin {
	public String nearestPalindromic(String n) {
		// 复制n的前len/2位，替换掉后面的len/2位，并计算加1、减1的情况。
		// 特殊情况有：
		// 个位数；
		// 进位的边界，比如10，100。
		// 与自身相等。
		if (n.length() == 1) return String.valueOf(Integer.parseInt(n) - 1);
		long nl = Long.parseLong(n);
		long oneZeros = (long)Math.pow(10, n.length() - 1);
		if (nl == oneZeros) return String.valueOf(oneZeros - 1);
		if (nl == oneZeros + 1) return String.valueOf(oneZeros - 1);
		if (nl == oneZeros * 10 - 1) return String.valueOf(nl + 2);
		Long s = mirror(n.toCharArray()), n1, n2;
		Long diff = s - nl;
		Long one = (long)Math.pow(10, n.length()/2);
		n1 = diff < 0 ? s : mirror(String.valueOf(nl - one).toCharArray());
		n2 = diff > 0 ? s : mirror(String.valueOf(nl + one).toCharArray());
		Long diff1 = diff < 0 ? -diff : (nl - n1);
		Long diff2 = diff > 0 ?  diff : (n2 - nl);
		return String.valueOf(diff1 <= diff2 ? n1 : n2);
	}

	private Long mirror(char[] chars) {
		StringBuilder out = new StringBuilder();
		int mid = (chars.length + 1)/2;
		for (int i=0; i<mid; i++) {
			out.append(chars[i]);
		}
		for (int i=0; i<chars.length - mid; i++) {
			out.append(chars[chars.length - mid - i - 1]);
		}
		return Long.parseLong(out.toString());
	}

	public static void main(String[] args){
		FindClosestPalin main = new FindClosestPalin();
		ResultCheck.check(main.nearestPalindromic("9"), "8");
		ResultCheck.check(main.nearestPalindromic("1"), "0");
		ResultCheck.check(main.nearestPalindromic("11"), "9");
		ResultCheck.check(main.nearestPalindromic("1213"), "1221");
		ResultCheck.check(main.nearestPalindromic("99"), "101");
		ResultCheck.check(main.nearestPalindromic("11911"), "11811");
		ResultCheck.check(main.nearestPalindromic("1283"), "1331");
		ResultCheck.check(main.nearestPalindromic("1000"), "999");
	}
}
