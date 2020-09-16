package leetcode.hard.dynamic;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.List;

/**
 * Created by 曹云 on 2020/9/16.
 * 139. 单词拆分
 * https://leetcode-cn.com/problems/word-break/solution/
 */
public class WordBreak {
	public boolean wordBreak(String s, List<String> wordDict) {
		return false;
	}

	public static void main(String[] args){
		WordBreak main = new WordBreak();
		ResultCheck.check(main.wordBreak("leetcode", Lists.newArrayList("leet", "code")), true);
		ResultCheck.check(main.wordBreak("applepenapple", Lists.newArrayList("apple", "pen")), true);
		ResultCheck.check(main.wordBreak("catsandog", Lists.newArrayList("cats", "dog", "sand", "and", "cat")), false);
	}
}
