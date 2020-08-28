package leetcode.medium.math;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/28.
 * 171. Excel表列序号
 * https://leetcode-cn.com/problems/excel-sheet-column-number/solution/
 */
public class ExcelSheetColumnNumber {
	public int titleToNumber(String s) {
		int num = 0;
		for (int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			num *= 26;
			num += (c - 'A' + 1);
		}
		return num;
	}

	public static void main(String[] args){
		ExcelSheetColumnNumber main = new ExcelSheetColumnNumber();
		ResultCheck.check(main.titleToNumber("AB"), 28);
		ResultCheck.check(main.titleToNumber("A"), 1);
		ResultCheck.check(main.titleToNumber("ZY"), 701);
	}
}
