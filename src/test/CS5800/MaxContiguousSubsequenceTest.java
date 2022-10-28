package CS5800;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxContiguousSubsequenceTest {

    @Test
    void getMaxArray() {
        MaxContiguousSubsequence main = new MaxContiguousSubsequence();
        Assertions.assertArrayEquals(new int[]{}, main.getMaxArray(new int[]{}));
        Assertions.assertArrayEquals(new int[]{}, main.getMaxArray2(new int[]{}));
        Assertions.assertArrayEquals(new int[]{10, -5, 40, 10,}, main.getMaxArray(new int[]{5, 15, -30, 10, -5, 40, 10}));
        Assertions.assertArrayEquals(new int[]{10, -5, 40, 10,}, main.getMaxArray2(new int[]{5, 15, -30, 10, -5, 40, 10}));
        Assertions.assertArrayEquals(new int[]{-1}, main.getMaxArray(new int[]{-1, -2, -3, -4}));
        Assertions.assertArrayEquals(new int[]{-1}, main.getMaxArray2(new int[]{-1, -2, -3, -4}));
        Assertions.assertArrayEquals(new int[]{-1}, main.getMaxArray(new int[]{-4, -3, -2, -1}));
        Assertions.assertArrayEquals(new int[]{-1}, main.getMaxArray2(new int[]{-4, -3, -2, -1}));
    }

}