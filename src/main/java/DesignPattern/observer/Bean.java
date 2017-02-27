package DesignPattern.observer;

import java.util.Observable;

public class Bean extends Observable {


	public void view() {
		System.out.println("view");
		this.setChanged();
		this.notifyObservers();
	}

}
