package Base;

/**
 * Created by 曹云 on 2021/1/19.
 */
public class OverrideChild extends OverrideParent {
	public int a = 2;

	@Override
	public void functionA() {
		System.out.println("child a");
	}

	public static void main(String[] args) {
		OverrideChild child = new OverrideChild();
		child.functionA();
		System.out.println(child.a);
		OverrideParent parent = (OverrideParent) child;
		parent.functionA();
		System.out.println(parent.a);
		OverrideParent parent1 = new OverrideParent();
		parent1.functionA();
		System.out.println(parent1.a);
	}
}
