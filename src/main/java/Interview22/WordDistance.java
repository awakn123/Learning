package Interview22;

public class WordDistance {
    public int calculate(String searchWord, String resultWord) {
        char[] searchChars = searchWord.toCharArray();
        char[] resultChars = resultWord.toCharArray();
        int j = 0;
        for (int i = 0; i < searchChars.length; i++) {
            if (searchChars[i] == resultChars[j]) {
                j++;
            }
            if (j == resultChars.length)
                break;
        }
        return resultChars.length - j;
    }
}
