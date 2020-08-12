package leetcode.easy.array;

import java.util.Arrays;

/**
 * Created by 曹云 on 2020/8/3.
 * 66. Plus One
 * https://leetcode.com/problems/plus-one/
 */
public class PlusOne {
	/**
	 * O(n)~O(3n)
	 * @param digits
	 * @return
	 */
	public int[] plusOne(int[] digits) {
		if (digits == null || digits.length == 0) return new int[]{};
		int[] result = new int[digits.length];
		boolean plusone = true;
		for (int i=digits.length-1; i>=0; i--) {
			result[i] = digits[i];
			if (plusone) {
				result[i]++;
				if (result[i] == 10) {
					result[i] = 0;
				} else {
					plusone = false;
				}
			}
		}
		if (plusone) {
			result = Arrays.copyOf(result, result.length + 1);
			for (int i=result.length-1; i>0; i--) {
				result[i] = result[i-1];
			}
			result[0] = 1;
		}
		return result;
	}

	/**
	 * not track all.
	 * see and reimplement
	 * @param digits
	 * @return
	 */
	public int[] plusOne2(int[] digits) {
		if (digits == null || digits.length == 0) return new int[]{};
		for (int i=digits.length-1; i>=0; i--) {
			digits[i]++;
			if (digits[i] == 10) {
				digits[i] = 0;
			} else {
				return digits;
			}
		}
		digits = Arrays.copyOf(digits, digits.length + 1);
		for (int i=digits.length-1; i>0; i--) {
			digits[i] = digits[i-1];
		}
		digits[0] = 1;
		return digits;
	}
	public static void main(String[] args){

		PlusOne main = new PlusOne();
		int[] digitsI = {1,2,3,4,5};
		int[] answerI = {1,2,3,4,6};
		int[] digitsII = {9,9,9,9};
		int[] answerII = {1,0,0,0,0};
		int[] digitsIII = {1,0,9,9,9};
		int[] answerIII = {1,1,0,0,0};
		int[] resultI = main.plusOne2(digitsI);
		int[] resultII = main.plusOne2(digitsII);
		int[] resultIII = main.plusOne2(digitsIII);
		checkResult(resultI, answerI);
		checkResult(resultII, answerII);
		checkResult(resultIII, answerIII);
	}

	public static void checkResult(int[] result, int[] answer) {
		if (result.length != answer.length) {
			System.out.println("Wrong, result.length: " + result.length + ", answer.length:" + answer.length + ".");
			return;
		}
		if (Arrays.equals(result, answer)) {
			System.out.println("Right");
		} else {
			System.out.println("Wrong");
			System.out.println("result:" + Arrays.toString(result));
			System.out.println("answer:" + Arrays.toString(answer));
		}
	}
}
