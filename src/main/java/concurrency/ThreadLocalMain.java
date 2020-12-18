package concurrency;

/**
 * Created by 曹云 on 2020/12/18.
 */
public class ThreadLocalMain {
	private static ThreadLocal<StringBuilder> builder = ThreadLocal.withInitial(StringBuilder::new);

	public static void main(String[] args)
	{
		for (int i = 0; i < 5; i++)
		{
			new Thread(() -> {
				String threadName = Thread.currentThread().getName();
				for (int j = 0; j < 3; j++)
				{
					append(j);
					System.out.printf("%s append %d, now builder value is %s, ThreadLocal instance hashcode is %d, ThreadLocal instance mapping value hashcode is %d\n", threadName, j, builder.get().toString(), builder.hashCode(), builder.get().hashCode());
				}

				change();
				System.out.printf("%s set new stringbuilder, now builder value is %s, ThreadLocal instance hashcode is %d, ThreadLocal instance mapping value hashcode is %d\n", threadName, builder.get().toString(), builder.hashCode(), builder.get().hashCode());
			}, "thread-" + i).start();
		}
	}

	private static void append(int num) {
		builder.get().append(num);
	}

	private static void change() {
		StringBuilder newStringBuilder = new StringBuilder("HelloWorld");
		builder.set(newStringBuilder);
	}

}
