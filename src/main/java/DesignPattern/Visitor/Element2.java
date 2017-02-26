package DesignPattern.Visitor;

/**
 * Created by 曹云 on 2017/2/26.
 */
public class Element2 implements Element {

	public void doSomething() {
		System.out.println("element2");
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
