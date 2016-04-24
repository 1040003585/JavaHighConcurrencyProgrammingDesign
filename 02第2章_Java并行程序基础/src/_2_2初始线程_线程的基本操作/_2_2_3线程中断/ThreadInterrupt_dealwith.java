package _2_2初始线程_线程的基本操作._2_2_3线程中断;

/**
 * 
 * @author admin：Wu_Being； Date&Time：2016-4-24 下午01:05:50；；
 */
public class ThreadInterrupt_dealwith {

	// /**
	// * Causes the currently executing thread to sleep (temporarily cease
	// * execution) for the specified number of milliseconds, subject to the
	// * precision and accuracy of system timers and schedulers. The thread does
	// * not lose ownership of any monitors.
	// *
	// * @param millis
	// * the length of time to sleep in milliseconds
	// *
	// * @throws IllegalArgumentException
	// * if the value of {@code millis} is negative
	// *
	// * @throws InterruptedException
	// * if any thread has interrupted the current thread. The
	// * <i>interrupted status</i> of the current thread is cleared
	// * when this exception is thrown.
	// *
	// */
	// public static native void sleep(long millis) throws InterruptedException;
	/*
	 * Java的native关键字 http://blog.csdn.net/jiakw_1981/article/details/3073613
	 * //public static native void sleep(long millis) throws
	 * InterruptedException;
	 * 
	 * Native Method:
	 * "A native method is a Java method whose implementation is provided by non-java code."
	 * http://www.jb51.net/article/79348.htm 详解Java中native关键字
	 */
	/**
	 * @throws InterruptedException
	 * 
	 */
	public static void main(String[] args) throws InterruptedException {

		Thread t1 = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				// super.run();
				while (true) {
					if (Thread.currentThread().isInterrupted()) {//tag
						System.out.println("Interrupted!");
						break;
					}
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {//tag-
						// TODO Auto-generated catch block
						System.out.println("Interrupted When Sleep.");
						// e.printStackTrace();
						// 中断处理：设置中断状态
						Thread.currentThread().interrupt();//tag+
					}
					System.out.println("do something...");
					Thread.yield();
				}
			}
		};
		t1.start();
		Thread.sleep(1000);
		t1.interrupt();//tag+
	}
}
/*无中断处理：
Interrupted When Sleep.
do something...
do something...
do something...
 */

/*有中断处理：
Interrupted When Sleep.
do something...
Interrupted!
 */