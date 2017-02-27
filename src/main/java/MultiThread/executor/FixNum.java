package MultiThread.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FixNum {
	public static void main(String[] args) {
		Executor executor = Executors.newFixedThreadPool(10);
		Runnable task = new Runnable(){
			@Override
			public void run() {
				System.out.printf("task\n");
			}
		};
		executor.execute(task);
		executor = Executors.newScheduledThreadPool(10);
		((ScheduledExecutorService)executor).scheduleAtFixedRate(task,1,1, TimeUnit.SECONDS);
	}
}
