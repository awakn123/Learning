package MultiThread.ForkJoin;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
	/**
	 * forkJoin可以支持多种写法，我存了三种。一种在这个Main里，一种在SumTask类里，还有一种是自己写的，FixedThreadNumTask，固定线程数。
	 * 这里的特殊在于，pool执行的是execute方法，而不是submit或者invoke;task中执行的是invoke方法，而不是fork、join；
	 * execute方法与submit方法类似，不过execute方法没有返回值，也只能接收task参数，submit方法有返回值，也可以接收callable接口。
	 * execute与submit是异步的，invoke是同步的。
	 * 如果用execute与submit，那么可以调用awaitTermination或者awaitQuiescence来等待执行结束
	 * task中的fork是异步调用，join是等待异步调用结束，invoke是同步调用。
	 *
	 * SumTask类中继承的是RecursiveTask,有返回值，使用的是fork,join,最后通过get来等待并取得数据。
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		ProductListGenerator productListGenerator = new ProductListGenerator();
		List<Product> products = productListGenerator.generate(10000);
		Task task = new Task(products, 0, products.size(), 0.2);

		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(task);
		do {
			System.out.printf("FixNum: Thread Count: %d\n",
					pool.getActiveThreadCount());
			System.out.printf("FixNum: Thread Steal: %d\n", pool.getStealCount());
			System.out.printf("FixNum: Parallelism: %d\n", pool.getParallelism());
			try {
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());

		pool.shutdown();

		if(task.isCompletedNormally()) {
			System.out.printf("FixNum: The process has completed normally.\n");
		}

		for(Product product : products) {
			if(product.getPrice() != 12) {
				System.out.printf("Product %s: %f\n",product.getName(),product.getPrice());
			}
		}

		System.out.println("FixNum: End of the program.\n");
	}
}
