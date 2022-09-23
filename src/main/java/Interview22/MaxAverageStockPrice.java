package Interview22;

/**
 * Get the max sum in k months, and there is no same price in the sibling months. If cannot find such a k-month, return -1;
 */
public class MaxAverageStockPrice {
    public int getMaxSum(int[] array, int k) {
        if (k > array.length || k == 0) {
            return -1;
        }
        int sum = -1, tmpSum = 0, start = 0;
        for (int i = 0; i < array.length; i++) {
            if (i > 0 && array[i] == array[i - 1]) {
                start = i;
                tmpSum = 0;
            }
            tmpSum = tmpSum + array[i];
            if (i - start >= k - 1) {
                if (sum < tmpSum) {
                    sum = tmpSum;
                }
                tmpSum = tmpSum - array[start];
                start++;
            }
        }
        return sum;
    }

}
