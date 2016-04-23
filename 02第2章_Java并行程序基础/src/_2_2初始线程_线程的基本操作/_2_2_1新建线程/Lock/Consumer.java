package _2_2��ʼ�߳�_�̵߳Ļ�������._2_2_1�½��߳�.Lock;

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
				lock.unlock(); // �����ͷ���
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

