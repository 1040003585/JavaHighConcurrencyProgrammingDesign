package _2_2初始线程_线程的基本操作._2_2_1新建线程.Lock;

import java.util.concurrent.locks.Lock;

public class Producer implements Runnable {

	private Lock lock;

	public Producer(Lock lock) {
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
				System.out.print("A");
			} finally {
				lock.unlock();
				try {
					Thread.sleep(90L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}