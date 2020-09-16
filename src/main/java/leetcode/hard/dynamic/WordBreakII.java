package leetcode.hard.dynamic;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import leetcode.util.ResultCheck;

import java.util.List;

/**
 * Created by 曹云 on 2020/9/16.
 * 140. Word Break II
 * https://leetcode-cn.com/problems/word-break-ii/solution/
 */
public class WordBreakII {
	public List<String> wordBreak(String s, List<String> wordDict) {

		return null;
	}

	public static void main(String[] args){
		WordBreakII main = new WordBreakII();
		ResultCheck.checkList(main.wordBreak("catsanddog", Lists.newArrayList("cat", "cats", "and", "sand", "dog")), Sets.newHashSet("cats and dog", "cat sand dog"));
		ResultCheck.checkList(main.wordBreak("pineapplepenapple", Lists.newArrayList("apple", "pen", "applenpen", "pine", "pineapple")),
				Sets.newHashSet("pine appe pen apple", "pineapple pen apple", "pine applepen apple"));
		ResultCheck.checkList(main.wordBreak("catsandog", Lists.newArrayList("cat", "cats", "and", "sand", "dog")), Sets.newHashSet());
	}
}
