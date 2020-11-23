package leetcode.hard.arrayandstring;

import leetcode.util.ResultCheck;

/**
 * Created by 曹云 on 2020/11/23.
 * https://leetcode-cn.com/problems/couples-holding-hands/
 */
public class CouplesHoldingHands {
	public int minSwapsCouples(int[] row) {
		int num = 0;
		for (int i=0; i<row.length; i+=2) {
			int key = row[i]/2;
			if (key != row[i+1]/2) {
				num++;
				// 这里搜索可以换成hash表记录位置，时间复杂度可以降低到O(n).
				for (int j=i+2; j<row.length; j++) {
					if (row[j]/2 == key) {
						int tmp = row[j];
						row[j] = row[i+1];
						row[i+1] = tmp;
					}
				}
			}
		}
		return num;
	}

	public static void main(String[] args){
		CouplesHoldingHands main = new CouplesHoldingHands();
		ResultCheck.check(main.minSwapsCouples(new int[]{0,2,1,3}), 1);
		ResultCheck.check(main.minSwapsCouples(new int[]{3,2,0,1}), 0);
	}
}
