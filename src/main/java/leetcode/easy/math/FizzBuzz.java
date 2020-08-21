package leetcode.easy.math;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 曹云 on 2020/8/16.
 * 412. Fizz Buzz
 * https://leetcode.com/problems/fizz-buzz/
 */
public class FizzBuzz {
	public List<String> fizzBuzz(int n) {
		List<String> result = new ArrayList<>(n);
		for (int i=1; i<=n; i++) {
			int mod3 = i%3, mod5 = i%5;
			if (mod3 == 0 && mod5 == 0) {
				result.add("FizzBuzz");
			} else if (mod3 == 0) {
				result.add("Fizz");
			} else if (mod5 == 0) {
				result.add("Buzz");
			} else {
				result.add(String.valueOf(i));
			}
		}
		return result;
	}
	public static void main(String[] args){
		FizzBuzz main = new FizzBuzz();
		ResultCheck.checkList(main.fizzBuzz(15), Lists.newArrayList("1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"));
	}
}
