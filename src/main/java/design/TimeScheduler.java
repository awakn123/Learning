package design;

import io.netty.util.HashedWheelTimer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * Created by 曹云 on 2020/12/13.
 */
public class TimeScheduler {
	public void delayQueue() {
		DelayQueue delayQueue = new DelayQueue();
	}

	public void hashedWheelTimer() {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
			//生成了一个时间轮，时间步长为300ms
			HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(300, TimeUnit.MILLISECONDS);
			System.out.println("start:" + LocalDateTime.now().format(formatter));
			//提交一个任务在三秒之后允许
			hashedWheelTimer.newTimeout(timeout -> {
				Thread.sleep(3000);//block
				System.out.println("task1:" + LocalDateTime.now().format(formatter));
			}, 3, TimeUnit.SECONDS);
			hashedWheelTimer.newTimeout(timeout ->
					System.out.println("task2:" + LocalDateTime.now().format(
							formatter)), 4, TimeUnit.SECONDS);
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			System.err.println(e);
		}
	}

	public void timer() {
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {

			}
		};
		Timer timer = new Timer();
	}

	public void scheduledExecutorService() {
		ScheduledExecutorService scheduledExecutorService =
				Executors.newScheduledThreadPool(5);

		ScheduledFuture scheduledFuture =
				scheduledExecutorService.schedule(
						new Callable() {
							public Object call() throws Exception {
								System.out.println("Executed!");
								return "Called!";
							}
						},
						5,
						TimeUnit.SECONDS);
	}

	public static void main(String[] args) {
		TimeScheduler main = new TimeScheduler();
		main.hashedWheelTimer();
		// long.max_value对应多少天、多少年。
//		long dayNum = TimeUnit.NANOSECONDS.toDays(Long.MAX_VALUE);
//		System.out.printf("long.max_value对应%d天，%d年。\n", dayNum, dayNum/365);
	}
}
