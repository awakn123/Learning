package leetcode.easy.string;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.*;

/**
 * Created by 曹云 on 2020/8/6.
 * 38. Count and Say
 * https://leetcode.com/problems/count-and-say/
 */
public class CountAndSay {

	public String countAndSay(int n) {
		if (n < 1 || n > 30) return "";
		if (n == 1) return "1";
		String s = "1";
		// 2 dimension array, [value,num];
		List<int[]> countList = new ArrayList<>();
		for (int i=1; i<n; i++) {
			for (int j=0;j<s.length();j++) {
				int num = Character.digit(s.charAt(j), 10);
				int lsize = countList.size();
				if (lsize == 0 || countList.get(lsize - 1)[0] != num) {
					int[] countArray = new int[]{num, 1};
					countList.add(countArray);
				} else {
					countList.get(lsize - 1)[1]++;
				}
			}
			s = "";
			for (int[] countArray: countList){
				s += countArray[1];// first num;
				s += countArray[0];// second value;
			}
			countList.clear();
		}
		return s;
	}


	/**
	 * see and reimplement.
	 * @param n
	 * @return
	 */
	public String countAndSay2(int n) {
		if (n < 1 || n > 30) return "";
		if (n == 1) return "1";
		String s = "1";
		StringBuilder out = new StringBuilder();
		while (n > 1) {
			int idx = 0;
			for (int i=1;i<s.length() + 1;i++) {
				if (i==s.length()) {
					out.append(i-idx).append(s.charAt(idx));
				} else if (s.charAt(i) != s.charAt(idx)) {
					out.append(i-idx).append(s.charAt(idx));
					idx = i;
				}
			}
			s = out.toString();
			out.delete(0, out.length());
			n--;
		}
		return s;
	}
	public static void main(String[] args){
		CountAndSay main = new CountAndSay();
		int nI = 1;
		String answerI = "1";
		String resultI = main.countAndSay2(nI);
		ResultCheck.check(resultI, answerI);
		int nII = 4;
		String answerII = "1211";
		String resultII = main.countAndSay2(nII);
		ResultCheck.check(resultII, answerII);
	}
}
