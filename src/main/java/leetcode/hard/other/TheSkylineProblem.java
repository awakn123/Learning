package leetcode.hard.other;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.*;

/**
 * Created by 曹云 on 2020/11/11.
 * https://leetcode-cn.com/problems/the-skyline-problem/solution/
 * 218. The Skyline Problem
 */
public class TheSkylineProblem {
	public List<List<Integer>> getSkyline(int[][] buildings) {
		if (buildings.length == 0)
			return new ArrayList<>();
		List<List<Integer>> result = merge(buildings, 0, buildings.length - 1);
		return result;
	}

	private List<List<Integer>> merge(int[][] buildings, int l, int r) {
		if (l == r) {
			return buildingToList(buildings[l]);
		}
		int mid = (l + r) >> 1;
		List<List<Integer>> leftList = merge(buildings, l, mid);
		List<List<Integer>> rightList = merge(buildings, mid + 1, r);
		return mergePos(leftList, rightList);
	}

	private List<List<Integer>> mergePos(List<List<Integer>> leftList, List<List<Integer>> rightList) {
		List<List<Integer>> out = new ArrayList<>();
		int l = 0, r = 0;
		int lY = 0, rY = 0, x = 0;
		int currY = 0;
		while (l < leftList.size() && r < rightList.size()) {
			List<Integer> lp = leftList.get(l), rp = rightList.get(r);
			if (lp.get(0) < rp.get(0)) {
				x = lp.get(0);
				lY = lp.get(1);
				l++;
			} else {
				x = rp.get(0);
				rY = rp.get(1);
				r++;
			}
			int maxY = Math.max(lY, rY);
			if (currY != maxY) {
				updatePos(out, x, maxY);
				currY = maxY;
			}
		}
		appendPos(out, leftList, l);
		appendPos(out, rightList, r);
		return out;
	}

	private void appendPos(List<List<Integer>> out, List<List<Integer>> list, int start) {
		while (start < list.size()) {
			updatePos(out, list.get(start).get(0), list.get(start).get(1));
			start++;
		}
	}

	private void updatePos(List<List<Integer>> out, int x, int y) {
		if (out.isEmpty()) {
			out.add(new ArrayList<Integer>(){{add(x); add(y);}});
			return;
		}
		List<Integer> lastPos = out.get(out.size() - 1);
		if (lastPos.get(0) == x) {
			lastPos.set(1, y);
		} else if (lastPos.get(1) != y) {
			out.add(new ArrayList<Integer>(){{add(x); add(y);}});
		}
	}

	private List<List<Integer>> buildingToList(int[] building) {
		List<List<Integer>> out = new ArrayList<>();
		List<Integer> start = new ArrayList<>();
		start.add(building[0]);
		start.add(building[2]);
		List<Integer> end = new ArrayList<>();
		end.add(building[1]);
		end.add(0);
		out.add(start);
		out.add(end);
		return out;
	}

	public static void main(String[] args){
		TheSkylineProblem main = new TheSkylineProblem();
		List<List<Integer>> answer = Lists.newArrayList();
		answer.add(Lists.newArrayList(2,10));
		answer.add(Lists.newArrayList(3,15));
		answer.add(Lists.newArrayList(7,12));
		answer.add(Lists.newArrayList(12,0));
		answer.add(Lists.newArrayList(15,10));
		answer.add(Lists.newArrayList(20,8));
		answer.add(Lists.newArrayList(24,0));
		ResultCheck.checkTwoDimension(main.getSkyline(new int[][]{
				{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}
		}), answer);
	}
}
