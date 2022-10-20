package leetcode.hard.sordandsearch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KthSmallestInTwoSortedArraysTest {

    @Test
    void getKthSmallest() {
        KthSmallestInTwoSortedArrays kth = new KthSmallestInTwoSortedArrays();
        Assertions.assertEquals(3, kth.getKthSmallest(new int[]{1,3,5,7}, new int[]{2,4,6,8}, 3));
        Assertions.assertEquals(1, kth.getKthSmallest(new int[]{1,3,5,7}, new int[]{2,4,6,8}, 1));
        Assertions.assertEquals(8, kth.getKthSmallest(new int[]{1,3,5,7}, new int[]{2,4,6,8}, 8));
        Assertions.assertEquals(6, kth.getKthSmallest(new int[]{}, new int[]{2,4,6,8}, 3));
        Assertions.assertEquals(5, kth.getKthSmallest(new int[]{1,2,3,4}, new int[]{5,6,7,8}, 5));
    }
}