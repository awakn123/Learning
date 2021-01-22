package Base;

/**
 * Created by 曹云 on 2021/1/22.
 */
public class GenericTest<T>{
	private T val;

	public T getVal() {
		return val;
	}

	public void setVal(T val) {
		this.val = val;
	}

	public static void main(String[] args) {
		GenericTest<? super OverrideChild> genericTest = new GenericTest<>();
		genericTest.setVal(new OverrideChild());
		Object obj = genericTest.getVal();
//		OverrideParent overrideChild = genericTest.getVal(); // error
	}
}
