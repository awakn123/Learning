package CS5800;

import java.util.Arrays;

public class MaxContiguousSubsequence {

    public int[] getMaxArray(int[] nums) {
        if (nums.length == 0)
            return new int[]{};
        int max = nums[0], startIndex = 0, endIndex = 0;
        int currentMax = nums[0], currentStartIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (currentMax < 0) {
                currentMax = nums[i];
                currentStartIndex = i;
            } else {
                currentMax += nums[i];
            }
            if (max < currentMax) {
                max = currentMax;
                endIndex = i;
                startIndex = currentStartIndex;
            }
        }

        if (startIndex == -1 || endIndex == -1)
            return new int[]{};
        return Arrays.copyOfRange(nums, startIndex, endIndex + 1);
    }

    public int[] getMaxArray2(int[] nums) {
        // Check the zero length: if no number, return an empty array;
        if (nums.length == 0)
            return new int[]{};

        // define an array to store the max sum of the contiguous subsequence whose last element must be current number.
        int[] maxSumEndsWithCurrentNumber = new int[nums.length];
        // define an array to store the start index of the contiguous subsequence,
        // so we could get the array by the start index stored in there and end index -- the index of the iteration.
        int[] startIndexOfSequence = new int[nums.length];
        // initiate first elements of the two array
        maxSumEndsWithCurrentNumber[0] = nums[0];
        startIndexOfSequence[0] = 0;

        // Iterate the whole input array from 1
        // as the nums[0] has been handled in initial process.
        for (int i = 1; i < nums.length; i++) {
            // If the max sum of subsequence that ends with i-1 element is positive
            if (maxSumEndsWithCurrentNumber[i - 1] > 0) {
                // The current max sequence should contain them
                // so the max value should be previous max + current value(the current value must be contained!)
                maxSumEndsWithCurrentNumber[i] = maxSumEndsWithCurrentNumber[i - 1] + nums[i];
                // And the start index should be same with the previous one.
                startIndexOfSequence[i] = startIndexOfSequence[i - 1];
                // It can be regarded as we add current element into a copy of previous subsequence.
            } else {
                // If it is not positive, then current max sequence should only contain current number.
                maxSumEndsWithCurrentNumber[i] = nums[i];
                // and current max sequence array should start from current number.
                startIndexOfSequence[i] = i;
            }
        }

        // initiate the max for storing the max sum of subsequence in the whole array,
        // and the endIndex of the subsequence.
        int max = maxSumEndsWithCurrentNumber[0], endIndex = 0;
        for (int i = 1; i < maxSumEndsWithCurrentNumber.length; i++) {
            if (max < maxSumEndsWithCurrentNumber[i]) {
                max = maxSumEndsWithCurrentNumber[i];
                endIndex = i;
            }
        }
        // get the start index by the index of the max sum of subsequence.
        int startIndex = startIndexOfSequence[endIndex];
        // return the array. As the java api will exclude last position, we need add 1 to endIndex;
        return Arrays.copyOfRange(nums, startIndex, endIndex + 1);
    }

}
