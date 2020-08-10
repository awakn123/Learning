package leetcode.easy.string;

/**
 * Created by 曹云 on 2020/8/6.
 * 344. Reverse String
 * https://leetcode.com/problems/reverse-string/
 */
public class ReverseString {
	public void reverseString(char[] s) {

	}

	public static void main(String[] args){
		ReverseString main = new ReverseString();
		char[] stringI = {'a', 'b', 'c'};
		char[] answerI = {'c', 'b', 'a'};
		main.reverseString(stringI);
		checkResult(stringI, answerI);
		char[] stringII = {'i', 'n', 'p', 'u', 't'};
		char[] answerII = {'t', 'u', 'p', 'n', 'i'};
		main.reverseString(stringII);
		checkResult(stringII, answerII);
		char[] stringIII = {'f', 'o', 'l', 'l', 'o', 'w'};
		char[] answerIII = {'w', 'o', 'l', 'l', 'o', 'f'};
		main.reverseString(stringIII);
		checkResult(stringIII, answerIII);
	}

	public static void checkResult(char[] result, char[] answer) {
		if (result == answer) {
			System.out.println("pass");
			return;
		}
		if (result == null || answer == null) {
			System.out.println("Wrong, someone is null.");
			return;
		}
		if (result.length != answer.length) {
			System.out.printf("Wrong, result.length is %d, answer.length is %d.\n", result.length, answer.length);
			return;
		}
		for (int i=0;i<result.length;i++) {
			if (result[i] != answer[i]) {
				System.out.printf("Wrong, result[%d] is %c, answer[%d] is %c.\n", i, result[i], i, answer[i]);
				return;
			}
		}
		System.out.println("pass");
	}

}
