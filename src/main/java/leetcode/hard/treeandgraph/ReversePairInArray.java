package leetcode.hard.treeandgraph;

import leetcode.util.ResultCheck;

import java.util.*;

/**
 * Created by 曹云 on 2020/9/6.
 * 剑指 Offer 51. 数组中的逆序对
 * https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 * 与 315. 计算右侧小于当前元素的个数 是关联性题目。
 */
public class ReversePairInArray {
	public int reversePairs1(int[] nums) {
		if (nums.length < 2) return 0;
		int count = 0;
		for (int i = nums.length - 1; i >= 1; i--) {
			for (int j = i - 1; j >= 0; j--) {
				if (nums[j] > nums[i])
					count++;
			}
		}
		return count;
	}

	public int reversePairs2(int[] nums) {
		if (nums.length < 2) return 0;
		Set<Integer> set = new HashSet<>();
		for (int num: nums) {
			set.add(num);
		}
		int[] sortedNums = new int[set.size()];
		int idx = 0;
		for (int num: set) {
			sortedNums[idx++] = num;
		}
		Arrays.sort(sortedNums);

		int[] countArray = new int[set.size()];
		int[] sumArray = new int[nums.length];
		for (int i = nums.length - 1; i >=0; i--) {
			idx = getIdx(sortedNums, nums[i]);
			sumArray[i] = calculateSum(countArray, idx);
			update(countArray, idx);
		}
		int sum = 0;
		for (int sumi: sumArray) {
			sum += sumi;
		}

		return sum;
	}

	public int getIdx(int[] sortedNums, int value) {
		int left = 0, right = sortedNums.length;
		while (left < right) {
			int mid = (left + right)/2;
			if (sortedNums[mid] < value) {
				left = mid + 1;
			} else if (sortedNums[mid] > value) {
				right = mid;
			} else {
				return mid;
			}
		}
		return left;
	}

	private void update(int[] countArray, int idx) {
		while (idx < countArray.length) {
			countArray[idx]++;
			idx += lowbit(idx + 1);
		}
	}

	private int calculateSum(int[] countArray, int idx) {
		int sum = 0;
		idx--;
		while (idx >=0) {
			sum+= countArray[idx];
			idx -= lowbit(idx + 1);
		}
		return sum;
	}

	private int lowbit(int x) {
		return x & (-x);
	}

	int count = 0;
	public int reversePairs(int[] nums) {
		if (nums.length <= 1) return 0;
		merge(nums, 0, nums.length - 1);
		return count;
	}

	private int[] merge(int[] nums, int left, int right) {
		if (left == right)
			return new int[]{nums[left]};
		int mid = (left + right)/2;
		int[] leftList = merge(nums, left, mid);
		int[] rightList = merge(nums, mid + 1, right);
		int[] res = new int[right - left + 1];
		int l=0, r=0, idx = 0;
		int reverseLen = 0;
		while(l<leftList.length && r<rightList.length) {
			if (leftList[l] <= rightList[r]) {
				res[idx++] = leftList[l++];
				count+=reverseLen;
			} else {
				res[idx++]=rightList[r++];
				reverseLen++;
			}
		}
		while (l<leftList.length) {
			res[idx++] = leftList[l++];
			count+=reverseLen;
		}
		while (r<rightList.length) {
			res[idx++] = rightList[r++];
		}
		return res;
	}

	public static void main(String[] args) {
		ReversePairInArray main = new ReversePairInArray();
		ResultCheck.check(main.reversePairs(new int[]{7, 5, 6, 4}), 5);
		main.count = 0;
		ResultCheck.check(main.reversePairs(new int[]{1, 3, 2, 3, 1}), 4);
	}
}
