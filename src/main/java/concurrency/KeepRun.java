package concurrency;

import concurrency.reference.GCTarget;

/**
 * Created by 曹云 on 2020/12/18.
 */
public class KeepRun implements Runnable {

	private boolean cancel = false;

	@Override
	public void run() {
		GCTarget gcTarget = new GCTarget(Thread.currentThread().getName());
		ThreadLocal<GCTarget> threadLocal = ThreadLocalMemoryTest.threadLocal;
		threadLocal.set(gcTarget);
		System.out.println("initialized: "+threadLocal.get().id);
		threadLocal = null;
		while (!cancel) {
			System.out.println(getId() + " is doing things...");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
		}
		System.out.println(getId() + " has been done.");
	}

	public void cancel() {
		this.cancel = true;
	}

	public String getId() {
		if (ThreadLocalMemoryTest.threadLocal == null)
			return "null";
		return ThreadLocalMemoryTest.threadLocal.get().id;
	}
}
