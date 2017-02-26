package DesignPattern.Visitor;

/**
 * Created by 曹云 on 2017/2/26.
 */
public interface Element {
	void doSomething();
	void accept(Visitor visitor);
}
