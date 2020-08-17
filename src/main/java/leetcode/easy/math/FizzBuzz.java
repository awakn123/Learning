package leetcode.easy.math;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.List;

/**
 * Created by 曹云 on 2020/8/16.
 * 412. Fizz Buzz
 * https://leetcode.com/problems/fizz-buzz/
 */
public class FizzBuzz {
	public List<String> fizzBuzz(int n) {
		return null;
	}
	public static void main(String[] args){
		FizzBuzz main = new FizzBuzz();
		ResultCheck.checkListString(main.fizzBuzz(15), Lists.newArrayList("1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"));
	}
}
