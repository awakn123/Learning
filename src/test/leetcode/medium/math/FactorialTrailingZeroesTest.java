package leetcode.medium.math;

import design.FizzBuzzInterface;
import leetcode.medium.math.FactorialTrailingZeroes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by 曹云 on 2020/12/3.
 */
public class FactorialTrailingZeroesTest {
	@Test
	void testTrailingZeroes() {
		FactorialTrailingZeroes main = new FactorialTrailingZeroes();
		Assertions.assertEquals(0, main.trailingZeroes(3));
		Assertions.assertEquals(1, main.trailingZeroes(5));
		Assertions.assertEquals(7, main.trailingZeroes(30));
	}
}
