package leetcode.easy.other;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 曹云 on 2020/8/17.
 * 118. Pascal's Triangle
 * https://leetcode.com/problems/pascals-triangle/
 */
public class PascalTriangle {

	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> result = new ArrayList<>(numRows);
		for (int i=0; i<numRows; i++) {
			List<Integer> row = new ArrayList<>(i+1), prevRow = i > 0 ? result.get(i - 1) : null;
			for (int j=0; j<i+1; j++) {
				int val = 1;
				if (j > 0 && j < i) {
					val = prevRow.get(j-1) + prevRow.get(j);
				}
				row.add(val);
			}
			result.add(row);
		}
		return result;
	}

	public static void main(String[] args){
		PascalTriangle main = new PascalTriangle();
		List<List<Integer>> answer = Lists.newArrayList(
				Lists.newArrayList(1),
				Lists.newArrayList(1, 1),
				Lists.newArrayList(1, 2, 1),
				Lists.newArrayList(1, 3, 3, 1),
				Lists.newArrayList(1, 4, 6, 4, 1)
		);
		ResultCheck.checkTwoDimension(main.generate(5), answer);
	}
}
