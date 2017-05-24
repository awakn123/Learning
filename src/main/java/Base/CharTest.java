package Base;

public class CharTest {
	public static void main(String[] args) {
		numToChar(39);
		charToNum('\'');
	}

	private static void charToNum(char a) {
		int num = (int)a;
		System.out.printf("Char:Num %s:%s;\n",a, num);
	}

	private static void numToChar(int num) {
		char a = (char)num;
		System.out.printf("Num:Char %s:%s;\n",num,a);
	}
}
