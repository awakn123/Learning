package concurrency.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by 曹云 on 2020/12/18.
 */
public class GCTargetWeakReference extends WeakReference<GCTarget> {
	// 弱引用的ID
	public String id;
	public GCTarget strong;

	public GCTargetWeakReference(GCTarget gcTarget,GCTarget strongGCTarget,
								 ReferenceQueue<? super GCTarget> queue) {
		super(gcTarget, queue);
		this.id = gcTarget.id;
		this.strong = strongGCTarget;
	}

	@Override
	protected void finalize() {
		System.out.println("Finalizing GCTargetWeakReference " + id);
	}
}
