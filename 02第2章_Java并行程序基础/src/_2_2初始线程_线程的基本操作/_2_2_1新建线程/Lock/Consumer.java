package _2_2初始线程_线程的基本操作._2_2_1新建线程.Lock;

import java.util.concurrent.locks.Lock;

public class Consumer implements Runnable {

	private Lock lock;

	public Consumer(Lock lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int count = 10;
		while (count > 0) {
			try {
				lock.lock();
				count--;
				System.out.print("B");
			} finally {
				lock.unlock(); // 主动释放锁
				try {
					Thread.sleep(91L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}

