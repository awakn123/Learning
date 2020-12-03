package leetcode.medium.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by 曹云 on 2020/12/3.
 */
class PowxnTest {

	@Test
	void myPow() {
		Powxn main = new Powxn();
		Assertions.assertEquals(main.myPow(2, 10), 1024, 1e-7d);
		Assertions.assertEquals(main.myPow(2.1, 3), 9.261, 1e-7);
		Assertions.assertEquals(main.myPow(2, -2), 0.25,1e-7);
		Assertions.assertEquals(main.myPow(2, -2147483648), 0,1e-7);
	}
}