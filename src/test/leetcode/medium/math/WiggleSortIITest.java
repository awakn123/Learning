package leetcode.medium.math;

import leetcode.hard.sordandsearch.WiggleSortII;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created by 曹云 on 2020/12/12.
 */
public class WiggleSortIITest {

	@Test
	public void testWiggleSortII(){
		WiggleSortII main = new WiggleSortII();
		int[] numsI = new int[]{1,5,1,1,6,4};
		main.wiggleSort(numsI);
		Assertions.assertArrayEquals(numsI,new int[]{1,6,1,5,1,4}, Arrays.toString(numsI));
	}
}
