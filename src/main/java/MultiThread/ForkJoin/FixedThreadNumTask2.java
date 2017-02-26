package MultiThread.ForkJoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedThreadNumTask2 extends RecursiveAction {
	private List<Integer> list;

	private static AtomicInteger i = new AtomicInteger(-1);

	public FixedThreadNumTask2(List<Integer> list) {
		this.list = list;
	}

	@Override
	protected void compute() {
		int cur = i.incrementAndGet();// 注意：不能先get再incrementAndGet，同样会有并发问题
		while (i.intValue() < list.size() && getQueuedTaskCount() <= 3) {
			new FixedThreadNumTask2(list).fork();
		}
		System.out.println("value:"+list.get(cur));
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (i.intValue() < list.size() && getQueuedTaskCount() <= 4) {
			new FixedThreadNumTask2(list).fork();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		ForkJoinPool pool = new ForkJoinPool();
		// 思路2：内层放置AtomicInteger
		System.out.println("start");
		FixedThreadNumTask2 task = new FixedThreadNumTask2(list);
		pool.submit(task);
		Thread.sleep(30);
		System.out.println("end");
	}
}
