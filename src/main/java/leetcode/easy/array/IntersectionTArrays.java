package leetcode.easy.array;

import com.google.common.collect.Sets;

import java.util.*;

/**
 * Created by 曹云 on 2020/8/3.
 * 349. Intersection of Two Arrays
 * https://leetcode.com/problems/intersection-of-two-arrays/
 */
public class IntersectionTArrays {
	public int[] intersection(int[] nums1, int[] nums2) {
		if (nums1.length == 0 || nums2.length == 0) return new int[]{};
		Set<Integer> set = new HashSet<Integer>();
		Set<Integer> set2 = new HashSet<Integer>();
		for (int i :nums1){
			set.add(i);
		}
		for (int i :nums2) {
			if (set.contains(i)){
				set2.add(i);
			}
		}
		int[] result = new int[set2.size()];
		Iterator<Integer> iter = set2.iterator();
		int i=0;
		while(iter.hasNext()){
			result[i++] = iter.next();
		}
		return result;
	}

	public static void main(String[] args){
		int[] nums1I = {1,2,3,3,4};
		int[] nums2I = {2,3};
		Set<Integer> answerI = Sets.newHashSet(2,3);
		int[] nums1II = {5,6,7,8,9};
		int[] nums2II = {8,9,10,11,12};
		Set<Integer> answerII = Sets.newHashSet(8,9);
		int[] nums1III = {9,9,4,4,3};
		int[] nums2III = {3,4,9,9,0};
		Set<Integer> answerIII = Sets.newHashSet(3,4,9);
		IntersectionTArrays main = new IntersectionTArrays();
		int[] resultI = main.intersection(nums1I, nums2I);
		checkResult(resultI, answerI);
		int[] resultII = main.intersection(nums1II, nums2II);
		checkResult(resultII, answerII);
		int[] resultIII = main.intersection(nums1III, nums2III);
		checkResult(resultIII, answerIII);
	}

	public static void checkResult(int[] result, Set<Integer> answer) {
		if (result == null) {
			System.out.println("Wrong. result is null.");
			return;
		}
		if (result.length != answer.size()) {
			System.out.println("Wrong, result.length:"+ result.length + ", answer.length:"+answer.size());
			return;
		}
		for (int i: result) {
			if (answer.contains(i)) {
				answer.remove(i);
			} else {
				System.out.println("Wrong: not contain answer:" + i);
				return;
			}
		}
		System.out.println("Right.");
	}
}
