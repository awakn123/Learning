package Base;

import java.lang.invoke.MethodHandles;

public class OrTest {
	public static void main(String[] args) {
		System.out.println(MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED
				| MethodHandles.Lookup.PACKAGE | MethodHandles.Lookup.PUBLIC);
	}
}
