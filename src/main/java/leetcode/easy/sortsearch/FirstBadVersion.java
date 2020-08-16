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
		if (n == 0) return 0;
		int left = 0, right = n;
		while (right - left > 1) {
//			int mid = (left + right) >> 1;
//			if (mid < 0) {
//				mid = (left >> 1) + (right >> 1) + ((left%2) + (right%2))/2;
//			}
			int mid = left + (right - left) / 2;
			if (isBadVersion(mid)) {
				right = mid;
			} else {
				left = mid;
			}
		}
		return right;
	}

	public static void main(String[] args){
		FirstBadVersion main = new FirstBadVersion();
		main.badVersion = 4;
		int result = main.firstBadVersion(5);
		ResultCheck.check(result, main.badVersion);

		main.badVersion = 10;
		result = main.firstBadVersion(27);
		ResultCheck.check(result, main.badVersion);

		main.badVersion = 1702766719;
		result = main.firstBadVersion(2126753390);
		ResultCheck.check(result, main.badVersion);
	}
}
