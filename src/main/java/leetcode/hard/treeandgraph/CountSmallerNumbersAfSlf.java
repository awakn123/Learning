package leetcode.hard.treeandgraph;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.*;

/**
 * Created by 曹云 on 2020/9/3.
 * 315. 计算右侧小于当前元素的个数
 * https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/solution/
 */
public class CountSmallerNumbersAfSlf {
	public List<Integer> countSmaller1(int[] nums) {
		int n = nums.length;
		List<Integer> list = new ArrayList<>(n);
		int[] res = new int[n];
		for (int i=n-1; i>=0; i--) {
			int num = 0;
			for (int j= i + 1; j < n; j++) {
				if (nums[i] > nums[j])
					num++;
				else if (nums[i] == nums[j]) {
					num += res[j];
					break;
				}
			}
			res[i] = num;
		}

		for (int i=0; i<n; i++) {
			list.add(res[i]);
		}
		return list;
	}

	public List<Integer> countSmaller2(int[] nums) {
		// prepare the sorted nums and result.
		Set<Integer> set = new HashSet<>();
		for (int num: nums) {
			set.add(num);
		}
		int[] sortedNum = new int[set.size()];
		int idx = 0;
		for (int num: set) {
			sortedNum[idx++] = num;
		}
		Arrays.sort(sortedNum);
		int[] countArr = new int[sortedNum.length];
		List<Integer> result = new ArrayList<>();

		for (int i = nums.length - 1; i>=0; i--) {
			idx = getIdx(sortedNum, nums[i]);
			result.add(count(countArr, idx));
			update(countArr, idx);
		}

		Collections.reverse(result);
		return result;
	}

	private int getIdx(int[] sortedArr, int value) {
		int left = 0, right = sortedArr.length;
		while(left < right) {
			int mid = (left + right) /2;
			if (sortedArr[mid] > value) {
				right = mid;
			} else if (sortedArr[mid] < value) {
				left = mid + 1;
			} else {
				return mid;
			}
		}
		return left;
	}

	private int count(int[] countArr, int idx) {
		int count = 0, i = idx - 1;
		while(i >= 0) {
			count += countArr[i];
			i -= lowbit(i + 1);
		}
		return count;
	}

	private void update(int[] countArr, int idx) {
		int i = idx;
		while (i < countArr.length) {
			countArr[i]++;
			i += lowbit(i+1);
		}
	}

	private int lowbit(int num) {
		return num & (-num);
	}

	private int[] mergeCount;
	private int[] mergeIdx;
	private int[] copyIdx;
	public List<Integer> countSmaller(int[] nums) {
		if (nums.length == 0) return new ArrayList<>();
		if (nums.length == 1) return new ArrayList<Integer>(){{add(0);}};
		mergeCount = new int[nums.length];
		mergeIdx = new int[nums.length];
		copyIdx = new int[nums.length];
		for (int i=0; i<mergeIdx.length; i++)
			mergeIdx[i] = i;
		merge(nums, 0, nums.length - 1);
		List<Integer> list = new ArrayList<>();
		for (int count:mergeCount)
			list.add(count);
		return list;
	}

	private int[] merge(int[] nums, int left, int right) {
		if (left == right)
			return new int[]{nums[left]};
		int mid = (left + right)/2;
		int[] leftArr = merge(nums, left, mid);
		int[] rightArr = merge(nums, mid + 1, right);
		int[] result = new int[right - left + 1];
		int l = 0, r = 0, idx = 0;
		for (int i = left; i <= right; i++)
			copyIdx[i] = mergeIdx[i];
		while (l < leftArr.length && r < rightArr.length) {
			if (leftArr[l] <= rightArr[r]) {
				mergeIdx[left+idx] = copyIdx[left + l];
				mergeCount[mergeIdx[left+idx]] += r;
				result[idx++] = leftArr[l++];
			} else {
				mergeIdx[left+idx] = copyIdx[mid + r + 1];
				result[idx++] = rightArr[r++];
			}
		}

		while (l < leftArr.length) {
			mergeIdx[left+idx] = copyIdx[left + l];
			mergeCount[mergeIdx[left+idx]] += (right - mid);
			result[idx++] = leftArr[l++];
		}
		while (r < rightArr.length) {
			mergeIdx[left+idx] = copyIdx[mid + r + 1];
			result[idx++] = rightArr[r++];
		}
		return result;
	}

	public static void main(String[] args){
		CountSmallerNumbersAfSlf main = new CountSmallerNumbersAfSlf();
		ResultCheck.checkList(main.countSmaller(new int[]{5,2,6,1}), Lists.newArrayList(2,1,1,0));
		ResultCheck.checkList(main.countSmaller(new int[]{2,0,1}), Lists.newArrayList(2,0,0));
		ResultCheck.checkList(main.countSmaller(new int[]{-1, -1}), Lists.newArrayList(0,0));
		main.initPreSum();
		System.out.println(Arrays.toString(main.preSum));
		ResultCheck.check(main.calculatePreSum(1), 3);
		ResultCheck.check(main.calculatePreSum(7), 36);
		ResultCheck.check(main.calculatePreSum(5), 21);
	}

	private int[] nums = new int[]{1,2,3,4,5,6,7,8};
	private int[] preSum = new int[8];

	private void initPreSum() {
		for (int i = 0; i< nums.length; i++) {
//			for (int j=i; j > i - lowbit(i + 1); j--) {
//				preSum[i] += nums[j];
//			}

			int j = i;
			while (j<nums.length) {
				preSum[j] += nums[i];
				j += lowbit(j + 1);
			}
		}
	}
	private int calculatePreSum(int idx) {
		int sum = 0;
		while (idx >= 0) {
			sum += preSum[idx];
			idx -= lowbit(idx + 1);
		}
		return sum;
	}
}
