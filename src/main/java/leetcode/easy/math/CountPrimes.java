package leetcode.easy.math;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 曹云 on 2020/8/16.
 * 204. Count Primes
 * https://leetcode.com/problems/count-primes/
 */
public class CountPrimes {
	public int countPrimes2(int n) {
		if (n <= 2) return 0;
		List<Integer> primes = new ArrayList<>();
		primes.add(2);
		for (int i=3; i<n; i+=2) {
			int m = (int)Math.sqrt(i);
			for (int j=0; j < primes.size(); j++) {
				if (primes.get(j) > m) {
					primes.add(i);
					break;
				} else if (i%primes.get(j) == 0) {
					break;
				}
			}
		}
		return primes.size();
	}
	public int countPrimes(int n) {
		if (n<=2) return 0;
		boolean[] nums = new boolean[n];
		Arrays.fill(nums, true);
		nums[0] = false;
		nums[1] = false;
		for (int i=2; i<Math.sqrt(n); i++) {
			if (!nums[i])
				continue;
			for (int j = i * i; j < n; j += i)
				nums[j] = false;
		}
		int result = 0;
		for (int i=0;i<nums.length;i++) {
			if(nums[i])
				result++;
		}
		return result;
	}

	public static void main(String[] args){
		CountPrimes main = new CountPrimes();
		ResultCheck.check(main.countPrimes(10), 4);
	}
}
