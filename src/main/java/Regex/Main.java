package Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {
		String sql = "select 33+222";
		Pattern p = Pattern.compile("(\\+|\\|\\|).*?(\\+|\\|\\|)");
		Matcher m = p.matcher(sql);
		System.out.println(m.find());
	}
}
