package MultiThread.Atomic;

/**
 * Created by 曹云 on 2017/1/23.
 */
public class SubThread extends Thread {

	SubThread(){
		super();
		System.out.println("constructor");
	}
	static{
		System.out.println("static");
	}
	@Override
	public void run() {
		System.out.println("subThread run");
	}
}
