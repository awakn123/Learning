package leetcode.easy.array;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by 曹云 on 2020/8/3.
 * 350. Intersection of Two Arrays II
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/
 * https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
 */
public class IntersectionTArraysII {
	/**
	 * O(m+n)~O(m*n)
	 * if sorted, then I can check the size, if small < current, break;
	 * the 2nd, 3rd follow up question has been completed in it.
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] intersect(int[] nums1, int[] nums2) {
		if (nums1.length == 0 || nums2.length == 0) return new int[]{};
		int[] small, large;
		if (nums1.length<nums2.length) {
			small = nums1;
			large = nums2;
		} else {
			small = nums2;
			large = nums1;
		}
		int[] result = new int[small.length];
		int smallLength = small.length, resultIdx=0;
		for (int i=0;i<large.length;i++){
			int cur = large[i];
			for (int j=0;j<smallLength;j++){
				if (cur == small[j]) {
					result[resultIdx] = cur;
					resultIdx++;
					smallLength--;
					small[j] = small[smallLength];
					break;
				}
			}
		}
		return Arrays.copyOf(result, resultIdx);
	}

	/**
	 * hash.
	 * see answer and reimplement it.
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] intersect2(int[] nums1, int[] nums2) {
		Map<Integer, Integer> smallMap = Maps.newHashMap();
		Map<Integer, Integer> bigMap = Maps.newHashMap();
		int[] small = nums1.length < nums2.length ? nums1 : nums2;
		int[] large = nums1.length >= nums2.length ? nums1 : nums2;
		for (int i : small) {
			if (smallMap.containsKey(i)){
				smallMap.put(i, smallMap.get(i) + 1);
			} else {
				smallMap.put(i, 1);
			}
		}
		int[] result = new int[small.length];
		int resultIdx = 0;
		for (int i : large) {
			if (smallMap.containsKey(i) && smallMap.get(i) > 0) {
				result[resultIdx] = i;
				smallMap.put(i, smallMap.get(i) - 1);
				resultIdx++;
			}
		}
		return Arrays.copyOf(result, resultIdx);
	}

	/**
	 * two pointers.
	 * see answer and reimplement it.
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] intersect3(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int[] result= new int[nums1.length > nums2.length ? nums1.length : nums2.length];
		int i=0,j=0, resultIdx=0;
		while(i<nums1.length && j<nums2.length) {
			if (nums1[i] == nums2[j]){
				result[resultIdx] = nums1[i];
				resultIdx++;
				i++;
				j++;
			} else if (nums1[i] < nums2[j]) {
				i++;
			} else {
				j++;
			}
		}
		return Arrays.copyOf(result, resultIdx);
	}
	public static void main(String[] args){
		int[] nums1I = {1,2,3,3,4};
		int[] nums2I = {2,3,3};
		List<Integer> answerI = Lists.newArrayList(2,3,3);
		int[] nums1II = {5,6,7,8,9};
		int[] nums2II = {8,9,10,11,12};
		List<Integer> answerII = Lists.newArrayList(8,9);
		int[] nums1III = {9,9,4,4,3};
		int[] nums2III = {3,4,9,9,0};
		List<Integer> answerIII = Lists.newArrayList(3,4,9,9);
		IntersectionTArraysII main = new IntersectionTArraysII();
		int[] resultI = main.intersect3(nums1I, nums2I);
		checkResult(resultI, answerI);
		int[] resultII = main.intersect3(nums1II, nums2II);
		checkResult(resultII, answerII);
		int[] resultIII = main.intersect3(nums1III, nums2III);
		checkResult(resultIII, answerIII);
	}

	public static void checkResult(int[] result, List<Integer> answer) {
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
				answer.remove((Integer)i);
			} else {
				System.out.println("Wrong: not contain answer:" + i);
				return;
			}
		}
		System.out.println("Right.");
	}
}
