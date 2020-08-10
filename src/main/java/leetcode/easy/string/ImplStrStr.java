package leetcode.easy.string;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/6.
 * 28. Implement strStr()
 */
public class ImplStrStr {

	public int strStr(String haystack, String needle) {
		return 0;
	}

	public static void main(String[] args){
		ImplStrStr main = new ImplStrStr();
		String haystackI = "hello", needleI = "ll";
		int answerI = 2;
		int resultI = main.strStr(haystackI, needleI);
		ResultCheck.check(resultI, answerI);
		String haystackII = "aaaaa", needleII = "bba";
		int answerII = -1;
		int resultII = main.strStr(haystackII, needleII);
		ResultCheck.check(resultII, answerII);
	}
}
