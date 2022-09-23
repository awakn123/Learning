package Interview22;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxAverageStockPriceTest {

    @Test
    void getMaxSum() {
        MaxAverageStockPrice price = new MaxAverageStockPrice();
        Assertions.assertEquals(14, price.getMaxSum(new int[]{1,2,7,7,4,3,6}, 3));
        Assertions.assertEquals(-1, price.getMaxSum(new int[]{1,2,7,7,4,3,6}, 5));
        Assertions.assertEquals(-1, price.getMaxSum(new int[]{1,2,7}, 5));
        Assertions.assertEquals(9, price.getMaxSum(new int[]{1,2,2,3,4,4,6}, 3));
    }
}