package concurrency.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;

/**
 * Created by 曹云 on 2020/12/18.
 */
public class WeakReferenceTest {
	// 弱引用队列
	private final static ReferenceQueue<GCTarget> REFERENCE_QUEUE = new ReferenceQueue<>();

	public static void main(String[] args) {
		LinkedList<GCTargetWeakReference> gcTargetList = new LinkedList<>();

		// 创建弱引用的对象，依次加入链表中
		for (int i = 0; i < 5; i++) {
			GCTarget gcTarget = new GCTarget(String.valueOf(i));
			GCTarget strongGCTarget = new GCTarget(String.valueOf(i + 5));
			GCTargetWeakReference weakReference = new GCTargetWeakReference(gcTarget, strongGCTarget, REFERENCE_QUEUE);
			gcTargetList.add(weakReference);

			System.out.println("Just created GCTargetWeakReference obj: " +
					gcTargetList.getLast());
		}

		// 通知GC进行垃圾回收
		System.gc();

		try {
			// 休息几分钟，等待上面的垃圾回收线程运行完成
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 检查关联的引用队列是否为空，并将id置空。
		Reference<? extends GCTarget> reference;
		while((reference = REFERENCE_QUEUE.poll()) != null) {
			if(reference instanceof GCTargetWeakReference) {
				GCTargetWeakReference wr = (GCTargetWeakReference) (reference);
				System.out.println("In queue, id is: " + wr.id);
			}
			System.out.println("reference:"+reference);
		}
		System.out.println("gcTargetList last is null:" + (gcTargetList.getLast() == null));
		Object first = gcTargetList.getFirst();

//		System.gc();
//		try {
//			// 休息6s，等待上面的垃圾回收线程运行完成
//			Thread.sleep(6000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		// 什么也不会发生

		// 清除其它引用位置
		gcTargetList.clear();
		// 通知GC进行垃圾回收
		System.gc();

		try {
			// 休息6s，等待上面的垃圾回收线程运行完成
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("object:" + first);
		System.out.println("reference:"+reference);
		// reference 依旧不持有对象。
		// 对弱引用的强引用也会干扰弱引用的回收，而reference引用不会干扰。
		// 弱引用内部的object不会影响弱引用的回收，而弱引用中的强引用也会被正确的回收。
		// 所以为什么ThreadLocal中Entry.value会泄漏？
		// ThreadLocal中，Entry的引用没有被清理掉，只是里面的ThreadLocal被清理了，所以导致了泄漏。
	}
}
