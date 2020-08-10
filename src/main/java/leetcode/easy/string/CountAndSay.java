package leetcode.easy.string;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/6.
 * 38. Count and Say
 * https://leetcode.com/problems/count-and-say/
 */
public class CountAndSay {

	public String countAndSay(int n) {
		return "";
	}
	public static void main(String[] args){
		CountAndSay main = new CountAndSay();
		int nI = 1;
		String answerI = "1";
		String resultI = main.countAndSay(nI);
		ResultCheck.check(resultI, answerI);
		int nII = 4;
		String answerII = "1211";
		String resultII = main.countAndSay(nII);
		ResultCheck.check(resultII, answerII);
	}
}
