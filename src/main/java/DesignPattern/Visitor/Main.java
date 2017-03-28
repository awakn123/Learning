package DesignPattern.Visitor;

/**
 * Created by 曹云 on 2017/2/26.
 */
public class Main {
	public static void main(String[] args){
		Visitor v1 = new Visitor1();
		Visitor v2 = new Visitor2();
		Element e1 = new Element1();
		Element e2 = new Element2();
		e1.accept(v1);
		e1.accept(v2);
		e2.accept(v1);
		e2.accept(v2);
	}
}
