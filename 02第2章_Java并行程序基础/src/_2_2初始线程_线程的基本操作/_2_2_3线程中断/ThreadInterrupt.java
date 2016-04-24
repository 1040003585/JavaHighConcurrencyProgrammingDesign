package _2_2初始线程_线程的基本操作._2_2_3线程中断;

/**
 * 
 * @author admin：Wu_Being； Date&Time：2016-4-24 下午12:43:01；；
 */
public class ThreadInterrupt {

	/**
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				// super.run();
				while (true) {
					Thread.yield();
				}
			}
		};
		t1.start();
		Thread.sleep(2000);// Thread.sleep()是静态方法，也可t1.sleep();
		t1.interrupt();
	}
}
