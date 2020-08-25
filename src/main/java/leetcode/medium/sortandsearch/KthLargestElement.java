package leetcode.medium.sortandsearch;

import leetcode.util.ResultCheck;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by 曹云 on 2020/8/25.
 * 215. 数组中的第K个最大元素
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/
 */
public class KthLargestElement {

	public int findKthLargest1(int[] nums, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
		});
		for (int i = 0; i<nums.length; i++) {
			int num = nums[i];
			if (pq.size() < k) {
				pq.add(num);
			} else {
				if (pq.peek() < num) {
					pq.remove();
					pq.add(num);
				}
			}
		}
		return pq.poll();
	}

	public int findKthLargest2(int[] nums, int k) {
		return quicksort(nums, k, 0, nums.length - 1);
	}
	Random random = new Random();

	public Integer quicksort(int[] nums, int k, int left, int right) {
		int s = left;
		int r = random.nextInt(right - left + 1) + left;
		swap(nums, right, r);
		for (int i=left; i<right; i++) {
			if (nums[i] < nums[right]) {
				swap(nums, i, s);
				s++;
			}
		}
		swap(nums, s, right);
		if (s == nums.length - k) {
			return nums[s];
		}
		if (s > nums.length - k) {
			return quicksort(nums, k, left, s - 1);
		} else {
			return quicksort(nums, k, s + 1, right);
		}
	}

	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public int findKthLargest(int[] nums, int k) {
		int len = nums.length;
		for (int i=(len>>1) - 1; i>=0; i--) {
			heapsort(nums, i, len);
		}
		for (int i=len - 1; i>=0; i--) {
			if (i == len - k)
				return nums[0];
			swap(nums, 0, i);
			heapsort(nums, 0, i);
		}
		return nums[0];
	}

	private void heapsort(int[] nums, int parent, int end) {
		int son = 2 * parent + 1;
		if (son >= end)
			return;
		if (son + 1 < end && nums[son] < nums[son + 1])
			son++;
		if (nums[parent] < nums[son]) {
			swap(nums, parent, son);
			heapsort(nums, son, end);
		}
	}

	public static void main(String[] args){
		KthLargestElement main = new KthLargestElement();
		ResultCheck.check(main.findKthLargest(new int[]{3,2,1,5,6,4}, 2), 5);
		ResultCheck.check(main.findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4), 4);
		ResultCheck.check(main.findKthLargest(new int[]{2,1}, 1), 2);
	}
}
