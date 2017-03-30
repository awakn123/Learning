package Base;

public class CharTest {
	public static void main(String[] args) {
		// char码值对应列表
		// http://www.cnblogs.com/tian_z/archive/2010/08/06/1793736.html
		/*for (int i=0;i<133;i++) {
			char a = (char)i;
			System.out.printf("Char(%s):%s;\n",i,a);
		}*/
		/*char i = 32;
		System.out.println("32:"+i+";");
		i = 9;
		System.out.println("9:"+i+";");
		String space = " ";
		String tab = "	";
		System.out.println("space=32:" + ((space.charAt(0)) == (char) 32));
		System.out.println("tab=9:" + ((tab.charAt(0)) == (char) 9));*/
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
