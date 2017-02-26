package MultiThread.Atomic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 曹云 on 2017/1/22.
 */
public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		// 多进程调用 -- 调用 ipconfig all 命令
		System.out.println("main");
		Process process = Runtime.getRuntime().exec("ipconfig /all");
		BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = null;
		while ((line = input.readLine()) != null) {
			System.out.println(line);
		}
		System.out.println("-------------------------------end1");

		ProcessBuilder builder =  new ProcessBuilder("ipconfig","/all");
		Process process2 = builder.start();
		builder.redirectErrorStream(true);
		process = builder.start();
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String readLine;
		while (null != (readLine = br.readLine())) {
			System.out.println(readLine);
		}

		System.out.println("----------------------------------end2");

		System.out.println("before new");
		SubThread subThread = new SubThread();
		System.out.println("after new");
		subThread.start();
		System.out.println("after start");

		AtomicInteger atomicInteger = new AtomicInteger(1);
		System.out.println("--初始值atomicInteger = " + atomicInteger);

		// 以原子方式将当前值减1，注意这里返回的是自减后的值
		System.out.println("atomicInteger.decrementAndGet() = " + atomicInteger.decrementAndGet());
		System.out.println("--自减后的 atomicInteger = " + atomicInteger);

		// 以原子方式将当前值加1，注意这里返回的是自增前的值
		System.out.println("atomicInteger.getAndIncrement() = " + atomicInteger.getAndIncrement());
		System.out.println("--自增后的 atomicInteger = " + atomicInteger);


		// 以原子方式将当前值与括号中的值相加，并返回结果
		System.out.println("atomicInteger.addAndGet(10) = " + atomicInteger.addAndGet(10));
		System.out.println("--增加10的 atomicInteger = " + atomicInteger);

		// 如果输入的值等于预期的值，则以原子方式将该值设置成括号中的值
		System.out.println("atomicInteger.compareAndSet(1, 2) = " + atomicInteger.compareAndSet(1, 2));
		System.out.println("--比较并设置1与2的 atomicInteger = " + atomicInteger);
		System.out.println("atomicInteger.compareAndSet(11, 9999) = " + atomicInteger.compareAndSet(11, 9999));
		System.out.println("--比较并设置11与9999的 atomicInteger = " + atomicInteger);
	}
}
