package DesignPattern.Adapter;

public class Impl extends AbstractImpl {

	@Override
	public void method1() {
		System.out.println("impl method1");
	}

	public static void main(String[] args) {
	    Impl impl = new Impl();
		impl.method1();
		impl.method2();
	}
}
