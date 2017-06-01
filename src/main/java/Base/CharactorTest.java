package Base;

import java.io.UnsupportedEncodingException;

public class CharactorTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
	    String s = "主目录";
		System.out.println(s);
		System.out.println(s.getBytes("GBK"));
		System.out.println(s.getBytes("UTF-8"));
		System.out.println(s.getBytes("ISO-8859-1"));
		printCharactor(s, "GBK");
		printCharactor(s, "UTF-8");
		printCharactor(s, "ISO-8859-1");
	}

	private static void printCharactor(String s, String originalCode) throws UnsupportedEncodingException {
		System.out.println(new String(s.getBytes(originalCode), "GBK"));
		System.out.println(new String(s.getBytes(originalCode), "UTF-8"));
		System.out.println(new String(s.getBytes(originalCode), "ISO-8859-1"));
	}
}
