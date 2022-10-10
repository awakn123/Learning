package Interview22;

import java.util.Arrays;

public class NineBoxKeyboard {
    public int findMinimumClickCount(String str) {
        if (str == null)
            return 0;
        int result = 0;
        char[] chars = str.toCharArray();
        int[] letterCount = new int[26];
        for (char c : chars) {
            letterCount[c - 'a']++;
        }
        Arrays.sort(letterCount);
        for (int i = 25; i >= 0; i--) {
            result += (letterCount[i] * ((25-i)/9 + 1));
        }
        return result;
    }

}
