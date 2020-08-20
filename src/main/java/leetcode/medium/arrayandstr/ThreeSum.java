package leetcode.medium.arrayandstr;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import leetcode.util.ResultCheck;

import java.util.*;

/**
 * Created by 曹云 on 2020/8/18.
 * 15. 3Sum
 * https://leetcode-cn.com/problems/3sum/solution/
 */
public class ThreeSum {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		int k = nums.length - 1;
		for (int i=0;i<k;i++) {
			for (int j=i+1; j<k;j++) {
				while (k - 1 > j && (nums[i] + nums[j] + nums[k] > 0)) {
					k--;
				}
				if (nums[i] + nums[j] + nums[k] == 0) {
					result.add(Arrays.asList(nums[i], nums[j], nums[k]));
				}
				while (j < k - 1 && nums[j] == nums[j+1]) {
					j++;
				}
			}
			k = nums.length - 1;
			while (i+1<k && nums[i] == nums[i+1]) {
				i++;
			}
		}

		return result;
	}

	public static void main(String[] args){
		ThreeSum main = new ThreeSum();
		List<List<Integer>> answer = Lists.newArrayList(
				Lists.newArrayList(-1,-1,2),
				Lists.newArrayList(-1,0,1)
		);
		ResultCheck.checkTwoDimension(main.threeSum(new int[]{-1, 0, 1, 2, -1, -4}), answer);
		List<List<Integer>> answerII = Lists.newArrayList(
				Lists.newArrayList(-5,1,4),
				Lists.newArrayList(-4,0,4),
				Lists.newArrayList(-4,1,3),
				Lists.newArrayList(-2,-2,4),
				Lists.newArrayList(-2,1,1),
				Lists.newArrayList(0,0,0)
		);
		ResultCheck.checkTwoDimension(main.threeSum(new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0}), answerII);
	}
}
