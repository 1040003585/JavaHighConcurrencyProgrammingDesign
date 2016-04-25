package _2_2初始线程_线程的基本操作._2_2_4等待wait和通知notify;

/**
 * 
 * @author admin：Wu_Being； Date&Time：2016-4-25 下午12:46:27；；
 */
public class SimpleWn {
	final static Object object = new Object();

	public static class T1 extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			// super.run();
			synchronized (object) {// T1获得object锁
				System.out.println(System.currentTimeMillis() + ":T1 start!");
				try {
					System.out.println(System.currentTimeMillis()
							+ ":T1 wait for object");
					//object.wait(); // T1等待，并释放object锁
					object.wait(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(System.currentTimeMillis() + ":T1 end!!!");

			}
		}

	}

	public static class T2 extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			// super.run();
			synchronized (object) {// T2获得object锁
				System.out.println(System.currentTimeMillis()
						+ ":T2 start!notify no thread");
				object.notify();// T2通知T1继续执行，但T1要等T2释放object锁后才能再获得object锁
				System.out.println(System.currentTimeMillis() + ":T2 end");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
			}// T2释放object锁
		}
	}

	public static void main(String[] args) {
		Thread t1 = new T1();
		Thread t2 = new T2();
		t1.start();
		t2.start();

	}
}
/**
 * 1461560224656:T1 start!
 * 
 * 1461560224656:T1 wait for object
 * 
 * 1461560224656:T2 start!notify no thread
 * 
 * 1461560224656:T2 end
 * 
 * 1461560226656:T1 end!!! ///+2000
 */
