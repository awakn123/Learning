package DesignPattern.Visitor;

/**
 * Created by 曹云 on 2017/2/26.
 */
public class Element1 implements Element {
	public void doSomething() {
		System.out.println("element1");
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
