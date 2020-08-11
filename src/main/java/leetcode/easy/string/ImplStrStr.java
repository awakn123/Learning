package leetcode.easy.string;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/6.
 * 28. Implement strStr()
 * https://leetcode.com/problems/implement-strstr/
 */
public class ImplStrStr {

	public int strStr(String haystack, String needle) {
		if (haystack == null || needle == null) return -1;
		if (haystack.equals(needle) || needle.length() == 0) return 0;
		if (haystack.length() < needle.length()) return -1;
		for (int i=0;i<haystack.length() + 1 - needle.length();i++) {
			if (haystack.charAt(i) == needle.charAt(0)) {
				boolean pass = true;
				for (int j = 1;j<needle.length();j++) {
					if (haystack.charAt(i+j) != needle.charAt(j)) {
						pass = false;
						break;
					}
				}
				if (pass) {
					return i;
				}
			}
		}

		return -1;
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
