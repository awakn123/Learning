package MultiThread.ForkJoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class FixedThreadNumTask extends RecursiveAction {
	private Integer value;
	public FixedThreadNumTask(Integer value) {
		this.value = value;
	}

	@Override
	protected void compute() {
		System.out.println("value:"+this.value);
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		List<Integer> list = new ArrayList<>();
		for (int i=0;i<10;i++) {
			list.add(i);
		}
		ForkJoinPool pool = new ForkJoinPool();
		// 思路1：外层控制
		System.out.println("start");
		int prev = 0;
		for (int i=0;i<3;i++) {
			FixedThreadNumTask task = new FixedThreadNumTask(list.get(prev));
			pool.submit(task);
			prev++;
		}
		while(prev < list.size()) {
			Thread.sleep(1);
			while (pool.getActiveThreadCount() < 3 && prev < list.size()) {
				FixedThreadNumTask task = new FixedThreadNumTask(list.get(prev));
				pool.submit(task);
				prev++;
			}
		}
		System.out.println("end");
	}
}
