package leetcode.hard.arrayandstring;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/30.
 * 11. 盛最多水的容器
 * https://leetcode-cn.com/problems/container-with-most-water/solution/
 */
public class ContainerWithMostWater {
	public int maxArea(int[] height) {
		int max = 0;
		int left = 0, right = height.length - 1;
		while (left < right) {
			int tmpMax = (right - left) * Math.min(height[left], height[right]);
			if (tmpMax > max)
				max = tmpMax;
			if (height[left] < height[right]) {
				while(left < right && height[left] >= height[left + 1])
					left++;
				left++;
			} else {
				while (right > left && height[right] >= height[right - 1])
					right--;
				right--;
			}
		}
		return max;
	}

	public static void main(String[] args){
		ContainerWithMostWater main = new ContainerWithMostWater();
		ResultCheck.check(main.maxArea(new int[]{1,8,6,2,5,4,8,3,7}), 49);
	}
}
