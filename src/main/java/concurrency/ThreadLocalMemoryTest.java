package concurrency;

import concurrency.reference.GCTarget;

/**
 * Created by 曹云 on 2020/12/18.
 */
public class ThreadLocalMemoryTest {

	public static ThreadLocal<GCTarget> threadLocal = new ThreadLocal();
	public static void main(String[] args) {
		Thread[] threadArr = new Thread[5];
		KeepRun[] runArr = new KeepRun[5];
		for (int i=0;i<5;i++) {
			runArr[i] = new KeepRun();
			threadArr[i] = new Thread(runArr[i],"thread-" + i);
			threadArr[i].start();
		}
		// Nothing will be finalized.
		gcAndSleep();
		for (int i = 0;i<threadArr.length;i++) {
			System.out.printf("The state of thread-%d: %s.\n", i, threadArr[i].getState());
			System.out.println(threadArr[i]);
		}

		runArr[0].cancel();
		gcAndSleep();
		for (int i = 0;i<threadArr.length;i++) {
			System.out.printf("The state of thread-%d: %s.\n", i, threadArr[i].getState());
			System.out.println(threadArr[i]);
		}

		System.out.println("set threadlocal to null.");
		threadLocal = null;
		gcAndSleep();
		for (int i = 0;i<threadArr.length;i++) {
			System.out.printf("The state of thread-%d: %s.\n", i, threadArr[i].getState());
			System.out.println(threadArr[i]);
		}
	}

	public static void gcAndSleep(){
		System.gc();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
