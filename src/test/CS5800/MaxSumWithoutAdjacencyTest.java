package CS5800;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxSumWithoutAdjacencyTest {

    @Test
    void getMaxSum() {
        MaxSumWithoutAdjacency main = new MaxSumWithoutAdjacency();
        int max = main.getMaxSum(new int[][]{
                {5, 8, 0, 8, 7},
                {5, 4, 10, 7, 18},
                {7, 16, 1, 14, 7},
                {4, 17, 2, 14, 6},
        });
        Assertions.assertEquals(83, max);
    }
}