package DesignPattern.observer;

public class Main {
	public static void main(String[] args) {
	    Bean bean = new Bean();
		BeanStat beanStat = new BeanStat();
		bean.addObserver(beanStat);
		System.out.println(BeanStat.viewNum);
		bean.view();
		System.out.println(BeanStat.viewNum);
	}
}
