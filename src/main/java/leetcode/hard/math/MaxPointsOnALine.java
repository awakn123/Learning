package leetcode.hard.math;

import SqlToMysql.util.ListUtils;
import leetcode.util.ResultCheck;

import java.util.*;

/**
 * Created by 曹云 on 2020/11/10.
 * https://leetcode-cn.com/problems/max-points-on-a-line/solution/
 * 149. 直线上最多的点数
 */
public class MaxPointsOnALine {

	Map<String, Integer> lines;
	int[][] points;
	int duplicates;
	public int countMaxPointsOnALineThroughPointI(int i) {
		if (i >= points.length - 2)
			return points.length - i;
		lines.clear();
		duplicates = 1;
		int max = 0;
		for (int j = i + 1; j<points.length;j++) {
			if (points.length - j <= max)
				break;
			max = Math.max(calcLine(i, j), max);
		}
		return max + duplicates;
	}

	public int calcLine(int i, int j) {
		int x1 = points[i][0];
		int y1 = points[i][1];
		int x2 = points[j][0];
		int y2 = points[j][1];
		if (x1 == x2 && y1 == y2)
			duplicates++;
		else {
			String scopeStr;
			if (x2 != x1 && y2 != y1) {
				scopeStr = divide(y2 - y1, x2 - x1);
			} else if (x2 == x1) {
				scopeStr = "y";
			} else {
				scopeStr = "x";
			}
			lines.put(scopeStr, lines.getOrDefault(scopeStr, 0) + 1);
			return lines.get(scopeStr);
		}
		return 0;
	}

	private String divide(int y, int x) {
		boolean isPositive = true;
		int px = x, py = y;
		if (x < 0) {
			px = -x;
			isPositive = !isPositive;
		}
		if (y < 0) {
			py = -y;
			isPositive = !isPositive;
		}
		while (py != 0) {
			int tmp = px%py;
			px = py;
			py = tmp;
		}
		x = Math.abs(x/px);
		y = Math.abs(y/px);
		StringBuilder out = new StringBuilder(isPositive ? "+" : "-");
		out.append(x).append("/").append(y);
		return out.toString();
	}


	public int maxPoints(int[][] points) {
		if (points.length <= 2)
			return points.length;

		this.lines = new HashMap<>();
		this.points = points;
		int max = 0;
		for (int i=0; i<points.length; i++) {
			if (points.length - i <= max)
				break;
			max = Math.max(max,countMaxPointsOnALineThroughPointI(i));
		}
		return max;
	}

	public static void main(String[] args){
		MaxPointsOnALine main = new MaxPointsOnALine();
		ResultCheck.check(main.maxPoints(new int[][]{
				{1,1},{2,2},{3,3}
		}), 3);
		ResultCheck.check(main.maxPoints(new int[][]{
				{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}
		}), 4);
		ResultCheck.check(main.maxPoints(new int[][]{
				{2,3},{3,3},{-5,3}
		}), 3);
		ResultCheck.check(main.maxPoints(new int[][]{
				{15,12},{9,10},{-16,3},{-15,15},{11,-10},{-5,20},{-3,-15},{-11,-8},{-8,-3},{3,6},{15,-14},{-16,-18},{-6,-8},{14,9},{-1,-7},{-1,-2},{3,11},{6,20},{10,-7},{0,14},{19,-18},{-10,-15},{-17,-1},{8,7},{20,-18},{-4,-9},{-9,16},{10,14},{-14,-15},{-2,-10},{-18,9},{7,-5},{-12,11},{-17,-6},{5,-17},{-2,-20},{15,-2},{-5,-16},{1,-20},{19,-12},{-14,-1},{18,10},{1,-20},{-15,19},{-18,13},{13,-3},{-16,-17},{1,0},{20,-18},{7,19},{1,-6},{-7,-11},{7,1},{-15,12},{-1,7},{-3,-13},{-11,2},{-17,-5},{-12,-14},{15,-3},{15,-11},{7,3},{19,7},{-15,19},{10,-14},{-14,5},{0,-1},{-12,-4},{4,18},{7,-3},{-5,-3},{1,-11},{1,-1},{2,16},{6,-6},{-17,9},{14,3},{-13,8},{-9,14},{-5,-1},{-18,-17},{9,-10},{19,19},{16,7},{3,7},{-18,-12},{-11,12},{-15,20},{-3,4},{-18,1},{13,17},{-16,-15},{-9,-9},{15,8},{19,-9},{9,-17}
		}), 6);
		ResultCheck.check(main.maxPoints(new int[][]{
				{0,0},{94911151,94911150},{94911152,94911151}
		}), 2);
	}
}
