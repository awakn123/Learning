package leetcode.hard.sordandsearch;

import leetcode.util.ResultCheck;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by 曹云 on 2020/9/9.
 * 324. 摆动排序 II
 * https://leetcode-cn.com/problems/wiggle-sort-ii/solution/
 */
public class WiggleSortII {
	public void wiggleSort1(int[] nums) {
		Arrays.sort(nums);
		int[] tmp = Arrays.copyOf(nums, nums.length);
		boolean isEven = nums.length%2 == 0;
		int mid = nums.length >> 1;
		for (int i=0; i < mid; i++) {
			if (isEven) {
				nums[2*i] = tmp[mid - 1 - i];
			} else {
				nums[2*i] = tmp[mid - i];
			}
			nums[2*i + 1] = tmp[nums.length - 1 - i];
		}
		if (!isEven)
			nums[nums.length - 1] = tmp[0];
	}
	public void wiggleSort2(int[] nums) {
		int mid = nums.length >> 1;
		int midNum = getSortedNum(nums, mid, 0, nums.length - 1);
		// 3 partition.
		for (int i=0, j = 0, k = nums.length - 1; i<=k; i++) {
			if (nums[i] < midNum) {
				swap(nums, i, j++);
			} else if (nums[i] > midNum) {
				swap(nums, i--, k--);
			}
		}

		int[] copyNums = nums.clone();
		for (int i=0; i < mid; i++) {
			if (nums.length%2==0) {
				nums[2 * i] = copyNums[mid - 1 - i];
			} else {
				nums[2 * i] = copyNums[mid - i];
			}
			nums[2 * i + 1] = copyNums[nums.length - 1 - i];
		}
		if (nums.length%2 == 1)
			nums[nums.length - 1] = copyNums[0];
	}

	public void wiggleSort(int[] nums) {
		int mid = nums.length >> 1;
		int midNum = getSortedNum(nums, mid, 0, nums.length - 1);
		// 3 partition.
		// 虚地址技巧分析：
		// 1. nums.length | 1 意为将偶数长度转为大1的奇数，奇数长度不变。
		// 2. 1 * 2i 为将序号转为奇数，当超长时，取余，因第1步保证了奇数除数，取余的结果必定为偶数，且从0起按2递增。
		// 3. 因此，对一个长度为8的数组，i会沿着 1 3 5 7 0 2 4 6 的顺序前进。
		// 4. 而对k值，会沿着 6 4 2 0 7 5 3 1 后退。
		// 此虚地址实际是模拟了一个长度为2 nums.length - 1 的数组
		// 按 n 1 n 3 n 5 n 7 n 0 n 2 n 4 n 6 n 的顺序展开
		// 只是直接将0\2\4\6放回原位。
		for (int i=0, j = 0, k = nums.length - 1; i<=k; i++) {
			int ai = (1+2*i)%(nums.length|1);
			if (nums[ai] > midNum) {
				int aj = (1+2*j)%(nums.length|1);
				swap(nums, ai, aj);
				j++;
			} else if (nums[ai] < midNum) {
				int ak = (1+2*k)%(nums.length|1);
				swap(nums, ai, ak);
				i--;
				k--;
			}
		}

	}

	private Random random = new Random();
	private int getSortedNum(int[] nums, int numIdx, int left, int right) {
		if (left == right) return nums[left];
		int r = left + random.nextInt(right - left), big = left, small = left;
		swap(nums, r, right);
		while(big < right) {
			if (nums[big] <= nums[right]) {
				swap(nums, big++, small++);
			} else {
				big++;
			}
		}
		swap(nums, right, small);
		if (small == numIdx)
			return nums[small];
		else if (small < numIdx) {
			return getSortedNum(nums, numIdx, small + 1, right);
		} else {
			return getSortedNum(nums, numIdx, left, small - 1);
		}
	}



	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public static void main(String[] args){
		WiggleSortII main = new WiggleSortII();
		int[] numsI = new int[]{1,5,1,1,6,4};
		main.wiggleSort(numsI);
		ResultCheck.check(numsI, new int[]{1,6,1,5,1,4});
		int[] numsII = new int[]{1,2,3,4,5,6,7};
		main.wiggleSort(numsII);
		ResultCheck.check(numsII, new int[]{4,5,3,7,2,6,1});
		int[] numsIII = new int[]{1,3,2,2,3,1};
		main.wiggleSort(numsIII);
		ResultCheck.check(numsIII, new int[]{2,3,1,3,1,2});
		int[] numsIV = new int[]{1,1,2,1,2,2,1};
		main.wiggleSort(numsIV);
		ResultCheck.check(numsIV, new int[]{1,2,1,2,1,2,1});
		int[] numsV = new int[]{4,5,5,6};
		main.wiggleSort(numsV);
		ResultCheck.check(numsV, new int[]{5,6,4,5});
		int[] numsVI = new int[]{4,5,5,5,5,6,6,6};
		main.wiggleSort(numsVI);
		ResultCheck.check(numsVI, new int[]{5,6,5,6,5,6,4,5});
	}
}
