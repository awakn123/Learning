package leetcode.easy.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 曹云 on 2020/8/3.
 * 136. Single Number
 * https://leetcode.com/problems/single-number/
 */
public class SingleNumber {

	/**
	 * O(n) and extra space.
	 * @param nums
	 * @return
	 */
	public int singleNumber(int[] nums) {
		if (nums.length == 0) return 0;
		if (nums.length == 1) return nums[0];
		Set<Integer> set = new HashSet<>();
		for (int num: nums) {
			if (set.contains(num)) {
				set.remove(num);
			} else {
				set.add(num);
			}
		}
		return set.iterator().next();
	}

	/**
	 * copy from the solution.
	 * If we take XOR of zero and some bit, it will return that bit
	 * a⊕0=a
	 * If we take XOR of two same bits, it will return 0
	 * a⊕a=0
	 * a⊕b⊕a=(a⊕a)⊕b=0⊕b=b
	 * @param nums
	 * @return
	 */
	public int singleNumber2(int[] nums) {
		if (nums.length%2 == 0) return 0;
		if (nums.length == 1) return nums[0];
		int a = 0;
		for (int i : nums) {
			a ^= i;
		}
		return a;
	}

	public static void main(String[] args){
		int[] numsI = {1,1,2,3,3};
		int answerI = 2;
		int[] numsII = {1,2,3,4,4,3,5,2,1};
		int answerII = 5;
		int[] numsIII = {9,8,5,5,8,6,6,1,1,3,3};
		int answerIII = 9;
		SingleNumber main = new SingleNumber();
		int resultI = main.singleNumber2(numsI);
		checkResult(answerI, resultI);
		int resultII = main.singleNumber2(numsII);
		checkResult(answerII, resultII);
		int resultIII = main.singleNumber2(numsIII);
		checkResult(answerIII, resultIII);
	}

	public static void checkResult(int answer, int result){
		if (answer != result)
			System.out.println("Wrong. Result:" + result + ", answer:"+answer);
		else {
			System.out.println("Right.");
		}
	}
}
