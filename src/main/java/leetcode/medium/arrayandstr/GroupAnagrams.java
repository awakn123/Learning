package leetcode.medium.arrayandstr;

import com.google.common.collect.Lists;
import leetcode.util.ResultCheck;

import java.util.List;

/**
 * Created by 曹云 on 2020/8/18.
 * 49. Group Anagrams
 * https://leetcode-cn.com/problems/group-anagrams/solution/
 */
public class GroupAnagrams {
	public List<List<String>> groupAnagrams(String[] strs) {
		return null;
	}

	public static void main(String[] args){
		GroupAnagrams main = new GroupAnagrams();
		List<List<String>> answer = Lists.newArrayList(
				Lists.newArrayList("ate","eat","tea"),
				Lists.newArrayList("nat","tan"),
				Lists.newArrayList("bat")
		);
		ResultCheck.checkTwoDimension(main.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}), answer);
	}
}
