package leetcode.easy.other;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.List;

/**
 * Created by 曹云 on 2020/8/17.
 * 118. Pascal's Triangle
 * https://leetcode.com/problems/pascals-triangle/
 */
public class PascalTriangle {

	public List<List<Integer>> generate(int numRows) {
		return null;
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
