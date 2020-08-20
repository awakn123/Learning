package leetcode.medium.arrayandstr;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 曹云 on 2020/8/18.
 * 49. Group Anagrams
 * https://leetcode-cn.com/problems/group-anagrams/solution/
 */
public class GroupAnagrams {
	public List<List<String>> groupAnagrams2(String[] strs) {
		Map<String, List<String>> map = new HashMap<>();
		for (int i=0; i<strs.length; i++) {
			char[] chars = strs[i].toCharArray();
			Arrays.sort(chars);
			String str = String.valueOf(chars);
			List<String> l = map.getOrDefault(str, new ArrayList<>());
			l.add(strs[i]);
			map.put(str, l);
		}
		return new ArrayList(map.values());
	}

	/**
	 * 两种方式没有本质上的区别，都是排序方式。第二种只是空间换时间，而且换的还挺笨的，还是不要用的好，理解思路。
	 * @param strs
	 * @return
	 */
	public List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> map = new HashMap<>();
		for (int i=0;i<strs.length;i++) {
			int[] count = new int[26];
			for (int j=0;j<strs[i].length(); j++) {
				count[strs[i].charAt(j) - 'a']++;
			}
			StringBuilder out = new StringBuilder();
			for (int j=0; j<count.length; j++) {
				out.append("#").append(count[j]);
			}
			String str = out.toString();
			List<String> l = map.getOrDefault(str, new ArrayList<>());
			l.add(strs[i]);
			map.put(str, l);
		}
		return new ArrayList(map.values());
	}

	public static void main(String[] args){
		GroupAnagrams main = new GroupAnagrams();
		List<List<String>> answer = Lists.newArrayList(
				Lists.newArrayList("ate","eat","tea"),
				Lists.newArrayList("nat","tan"),
				Lists.newArrayList("bat")
		);
		ResultCheck.checkTwoDimension(main.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}), answer);
		List<List<String>> answerII = Lists.newArrayList(
				Lists.newArrayList("boo"),
				Lists.newArrayList("bob")
		);
		ResultCheck.checkTwoDimension(main.groupAnagrams(new String[]{"boo", "bob"}), answerII);
	}
}
