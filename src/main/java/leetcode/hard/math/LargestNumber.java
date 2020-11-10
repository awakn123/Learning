package leetcode.hard.math;

import leetcode.util.ResultCheck;

import java.util.*;

/**
 * Created by 曹云 on 2020/11/10.
 * https://leetcode-cn.com/problems/largest-number/
 * 179. 最大数
 */
public class LargestNumber {
	public String largestNumber(int[] nums) {
		List<String> numList = new ArrayList<>();
		for (int num: nums) {
			numList.add(String.valueOf(num));
		}
		numList.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				String order1 = o1 + o2;
				String order2 = o2 + o1;
				return order2.compareTo(order1);
			}
		});
		StringBuilder out = new StringBuilder();
		numList.forEach(out::append);
		if (out.charAt(0) == '0')
			return "0";
		return out.toString();
	}
	public static void main(String[] args){
		LargestNumber main = new LargestNumber();
		ResultCheck.check(main.largestNumber(new int[]{1}), "1");
		ResultCheck.check(main.largestNumber(new int[]{21, 0}), "210");
		ResultCheck.check(main.largestNumber(new int[]{99, 98}), "9998");
		ResultCheck.check(main.largestNumber(new int[]{3,30,34,5,9}), "9534330");
		ResultCheck.check(main.largestNumber(new int[]{34323, 3432}), "343234323");
	}
}
