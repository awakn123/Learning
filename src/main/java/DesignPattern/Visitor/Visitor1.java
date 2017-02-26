package DesignPattern.Visitor;

/**
 * Created by 曹云 on 2017/2/26.
 */
public class Visitor1 implements Visitor {

	public void visit(Element e) {
		System.out.print("Visitor1.visit");
		e.doSomething();
	}
}
