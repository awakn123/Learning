package leetcode.hard.treeandgraph;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.List;

/**
 * Created by 曹云 on 2020/9/3.
 * 127. 单词接龙
 * https://leetcode-cn.com/problems/word-ladder/solution/
 */
public class WordLadder {
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		return 0;
	}

	public static void main(String[] args){
		WordLadder main = new WordLadder();
		ResultCheck.check(main.ladderLength("hit", "cog", Lists.newArrayList("hot", "dot", "dog", "lot", "log", "cog")), 5);
		ResultCheck.check(main.ladderLength("hit", "cog", Lists.newArrayList("hot", "dot", "dog", "lot", "log")), 0);
	}
}
