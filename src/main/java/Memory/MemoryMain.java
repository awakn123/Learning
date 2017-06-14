package Memory;

import java.util.Date;

public class MemoryMain {
	public static void main(String[] args) {
		Runtime run = Runtime.getRuntime();
//		System.in.read();   // 暂停程序执行

		run.gc();
		System.out.println("time: " + (new Date()));
		// 获取开始时内存使用量
		long startMem = run.totalMemory()-run.freeMemory();
		System.out.println("memory> total:" + run.totalMemory() + " free:" + run.freeMemory() + " used:" + startMem );

		String str = "";
		for(int i=0; i<50000; ++i){
			str += i;
		}

		System.out.println("time: " + (new Date()));
		long endMem = run.totalMemory()-run.freeMemory();
		System.out.println("memory> total:" + run.totalMemory() + " free:" + run.freeMemory() + " used:" + endMem );
		System.out.println("memory difference:" + (endMem-startMem));
	}
}
