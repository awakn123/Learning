package leetcode.easy.sortsearch;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/8/12.
 * 278. First Bad Version
 * https://leetcode.com/problems/first-bad-version/
 */
public class FirstBadVersion {

	int badVersion = 0;
	boolean isBadVersion(int version) {
		return version >= badVersion;
	}
	public int firstBadVersion(int n) {
		return 0;
	}

	public static void main(String[] args){
		FirstBadVersion main = new FirstBadVersion();
		main.badVersion = 4;
		int result = main.firstBadVersion(5);
		ResultCheck.check(result, main.badVersion);

		main.badVersion = 10;
		result = main.firstBadVersion(27);
		ResultCheck.check(result, main.badVersion);
	}
}
