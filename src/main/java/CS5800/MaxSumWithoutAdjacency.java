package CS5800;

import java.util.Arrays;

/**
 * In 4(row) * n (column) matrix, deny vertical and horizontal adjacent selection, allow diagonal selection.
 * Get the max sum of selection in O(n)
 */
public class MaxSumWithoutAdjacency {
    private int[][] pattern = {
            {},//empty slot
            {1}, {2}, {3}, {4},//single node
            {1, 3}, {2, 4}, {1, 4},// two nodes
    };

    private int[][] compatible = {
            {1, 2, 3, 4, 5, 6, 7, 8},// patten 1: empty set can be adjacent with all other patterns.
            {1, 3, 4, 5, 8},// pattern 2
            {1, 2, 4, 5, 6, 7},// pattern 3
            {1, 2, 3, 5, 7, 8},// pattern 4
            {1, 2, 3, 4, 6},// pattern 5
            {1, 3, 5, 8},// pattern 6
            {1, 3, 4},// pattern 7
            {1, 2, 4, 6},// pattern 8
    };

    private int[][] patternMatrix;

    /**
     * get max sum from a 4*d array with no-adjacency restriction.
     * @param numsMatrix
     * @return
     */
    public int getMaxSum(int[][] numsMatrix) {
        // Corner check
        if (numsMatrix.length != 4) {
            throw new IllegalArgumentException("The function can only support 4 rows matrix, and current row number is:" + numsMatrix.length);
        }
        if (numsMatrix[0].length == 0)
            return 0;

        // create the 8*d pattern matrix.
        this.patternMatrix = new int[8][numsMatrix[0].length];
        // Initiate the first column with simply sum. Do not need to consider previous affection.
        for (int pattern = 1; pattern <= 8; pattern++) {
            this.patternMatrix[pattern - 1][0] = this.getColumnSumByPattern(numsMatrix, 0, pattern);
        }

        // Iterate from 2 ~ n, in java it is 1 ~ n - 1
        // calculate each cell value and store them one column by one column.
        for (int column = 1; column < numsMatrix[0].length; column++) {
            for (int pattern = 1; pattern <= 8; pattern++) {
                this.patternMatrix[pattern - 1][column] = this.getColumnValueByPattern(numsMatrix, column, pattern);
            }
        }

        // Compare all values in the last column and get and return the max value.
        int max = 0;
        for (int pattern = 1; pattern <= 8; pattern++) {
            int lastColumnValue = this.patternMatrix[pattern - 1][numsMatrix[0].length - 1];
            max = Math.max(max, lastColumnValue);
        }
        return max;
    }

    /**
     * get Column value by pattern.
     *
     * @param numsMatrix
     * @param column
     * @param patternIndex
     * @return
     */
    private int getColumnValueByPattern(int[][] numsMatrix, int column, int patternIndex) {
        // get the sum in current column with this pattern.
        int sumByPattern = getColumnSumByPattern(numsMatrix, column, patternIndex);
        // get the max value of previous column in these compatible patterns.
        int maxWithCompatiblePattern = getPreviousMaxWithCompatiblePattern(column, patternIndex);
        // the value would be the sum plus the max value.
        return sumByPattern + maxWithCompatiblePattern;
    }

    /**
     * get sum of this column with the pattern.
     *
     * @param numsMatrix
     * @param column
     * @param patternIndex
     * @return
     */
    private int getColumnSumByPattern(int[][] numsMatrix, int column, int patternIndex) {
        int sum = 0;
        // make the pattern index and row number suits with the java grammar: start from 0.
        for (int row : pattern[patternIndex - 1]) {
            // row is the row number that we defined in previous pattern 2d array.
            sum += numsMatrix[row - 1][column];
        }
        return sum;
    }

    /**
     * Get the max value of previous column in the restrictions of compatible pattern.
     *
     * @param column
     * @param patternIndex
     * @return
     */
    private int getPreviousMaxWithCompatiblePattern(int column, int patternIndex) {
        int max = 0;
        // compatible is previous compatible pattern 2d array that we defined.
        // make the pattern index and compatible pattern index suits with the java grammar: start from 0.
        for (int compatibleIndex : compatible[patternIndex - 1]) {
            // We are checking the max of the previous column, so the column need - 1.
            max = Math.max(max, patternMatrix[compatibleIndex - 1][column - 1]);
        }
        return max;
    }

}
