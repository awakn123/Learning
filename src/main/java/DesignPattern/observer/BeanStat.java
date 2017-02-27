package DesignPattern.observer;

import java.util.Observable;
import java.util.Observer;

public class BeanStat implements Observer {

	public static int viewNum = 0;

	@Override
	public void update(Observable o, Object arg) {
		viewNum++;
		System.out.println("update");
	}
}
