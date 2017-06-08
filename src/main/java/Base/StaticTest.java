package Base;

public class StaticTest {
	public StaticTest() {
		System.out.println("1");
	}

	public void call() {}

	public static void main(String[] args) {
		System.out.println("2");
		StaticInner.call();
	}
	public static class StaticInner{
		private StaticTest staticTest = new StaticTest();
		public static void call() {
//			staticTest.call();会报错
		}
	}
}
