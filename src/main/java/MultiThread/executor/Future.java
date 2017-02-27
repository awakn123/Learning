package MultiThread.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Future {

	public static void main(String[] args) {
		Callable<Integer> func = new Callable<Integer>() {
			public Integer call() throws Exception {
				System.out.println("call");
				Thread.sleep(10000);
				return 110;
			}
		};

		FutureTask<Integer> futureTask = new FutureTask<Integer>(func);

		Thread t = new Thread(futureTask);
		t.start();
		try {
			System.out.println("blocking...");
			int i = futureTask.get();
			System.out.println(i);
			System.out.println("end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}