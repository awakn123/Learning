package leetcode.hard.treeandgraph;

import com.google.common.collect.Lists;
import com.sun.tools.javac.util.Pair;
import leetcode.util.ResultCheck;

import java.util.*;

/**
 * Created by 曹云 on 2020/9/3.
 * 127. 单词接龙
 * https://leetcode-cn.com/problems/word-ladder/solution/
 */
public class WordLadder {
	public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
		Set<String> beginSet = new HashSet<>(), preBeginSet = new HashSet<>(), usedBeginSet = new HashSet<>(),
				endSet = new HashSet<>(), preEndSet = new HashSet<>(), usedEndSet = new HashSet<>();
		for (String word: wordList) {
			if (endWord.equals(word))
				preEndSet.add(word);
		}
		if (preEndSet.size() == 0)
			return 0;
		preBeginSet.add(beginWord);
		int beginCount = 1;
		int endCount = 0;
		while(true) {
			for (String word: wordList) {
				if (!usedBeginSet.contains(word)) {
					for (String str: preBeginSet) {
						if (ladderDiff(str, word) == 1)
							beginSet.add(word);
					}
				}
				if (!usedEndSet.contains(word)) {
					for (String str: preEndSet) {
						if (ladderDiff(str, word) == 1)
							endSet.add(word);
					}
				}
			}
			if (beginSet.size() == 0 && endSet.size() == 0)
				return 0;
			if (beginSet.size() == 0 && preBeginSet.size() == 0)
				return 0;
			if (endSet.size() == 0 && preEndSet.size() == 0)
				return 0;
			beginCount++;
			endCount++;
			if (check(preBeginSet, endSet))
				return beginCount + endCount - 1;
			if (check(beginSet, preEndSet))
				return beginCount + endCount - 1;
			if (check(beginSet, endSet))
				return beginCount + endCount;

			usedBeginSet.addAll(beginSet);
			preBeginSet = beginSet;
			beginSet = new HashSet<>();
			usedEndSet.addAll(endSet);
			preEndSet = endSet;
			endSet = new HashSet<>();
		}
	}

	private int ladderDiff(String word1, String word2) {
		char[] wordChar1 = word1.toCharArray(), wordChar2 = word2.toCharArray();
		int c = 0;
		for (int i=0; i<wordChar1.length; i++) {
			if (wordChar1[i] != wordChar2[i])
				c++;
		}
		return c;
	}
	private boolean check(Set<String> set1, Set<String> set2) {
		for (String str: set1) {
			if (set2.contains(str))
				return true;
		}
		return false;
	}

	private Map<String, Set<String>> wordChangeMap = new HashMap<>();
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		if (!wordList.contains(endWord))
			return 0;
		wordChangeMap.clear();
		int len = beginWord.length();
		for (String word: wordList) {
			for (int i=0; i<len; i++) {
				String key = word.substring(0, i) + "*" + word.substring(i+1, len);
				Set<String> set = wordChangeMap.getOrDefault(key, new HashSet<>());
				set.add(word);
				wordChangeMap.put(key, set);
			}
		}
		Queue<Pair<String, Integer>> queueBegin = new LinkedList<>();
		Map<String, Integer> visitedBegin = new HashMap<>();
		queueBegin.add(new Pair<String, Integer>(beginWord, 1));
		Queue<Pair<String, Integer>> queueEnd = new LinkedList<>();
		Map<String, Integer> visitedEnd = new HashMap<>();
		visitedEnd.put(endWord, 1);
		queueEnd.add(new Pair(endWord, 1));

		while(!queueBegin.isEmpty() && !queueEnd.isEmpty()) {
			int result = walkQueue(queueBegin, visitedBegin, visitedEnd);
			if (result != -1)
				return result;
			result = walkQueue(queueEnd, visitedEnd, visitedBegin);
			if (result != -1)
				return result;
		}
		return 0;
	}
	private int walkQueue(Queue<Pair<String, Integer>> queue, Map<String, Integer> currentVisited, Map<String, Integer> endVisited) {
		Pair<String, Integer> pair = queue.remove();
		String key = pair.fst;
		Integer level = pair.snd;
		for (int i=0; i<key.length(); i++) {
			String changeKey = key.substring(0, i) + "*" + key.substring(i+1, key.length());
			for (String str: wordChangeMap.getOrDefault(changeKey, new HashSet<>())) {
				if (endVisited.containsKey(str))
					return level + endVisited.get(str);
				if (!currentVisited.containsKey(str)) {
					queue.add(new Pair(str, level + 1));
					currentVisited.put(str, level + 1);
				}
			}
		}
		return -1;
	}

	public static void main(String[] args){
		WordLadder main = new WordLadder();
		ResultCheck.check(main.ladderLength("hit", "cog", Lists.newArrayList("hot", "dot", "dog", "lot", "log", "cog")), 5);
		ResultCheck.check(main.ladderLength("hit", "cog", Lists.newArrayList("hot", "dot", "dog", "lot", "log")), 0);
		ResultCheck.check(main.ladderLength("red", "tax", Lists.newArrayList("ted", "tex", "red", "tax", "tad", "den", "rex", "pee")), 4);
		ResultCheck.check(main.ladderLength("hog", "cog", Lists.newArrayList("cog")), 2);
	}
}
