package leetcode.hard.other;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/11/11.
 * https://leetcode-cn.com/problems/trapping-rain-water/
 * 42. 接雨水
 */
public class TrappingRainWater {
	public int trap(int[] height) {
		if (height.length == 0) return 0;
		int l = 0, r = height.length - 1, water = 0;
		int borderH = Math.min(height[l], height[r]);
		while (l < r) {
			if (height[l] < height[r]) {
				if (height[l] < borderH) {
					water += (borderH - height[l]);
				}
				borderH = Math.min(Math.max(borderH, height[l]), height[r]);
				l++;
			} else {
				if (height[r] < borderH) {
					water += (borderH - height[r]);
				}
				borderH = Math.min(Math.max(borderH, height[r]), height[l]);
				r--;
			}
		}
		return water;
	}

	public static void main(String[] args){
		TrappingRainWater main = new TrappingRainWater();
		ResultCheck.check(main.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}), 6);
		ResultCheck.check(main.trap(new int[]{4,2,0,3,2,5}), 9);
	}
}
