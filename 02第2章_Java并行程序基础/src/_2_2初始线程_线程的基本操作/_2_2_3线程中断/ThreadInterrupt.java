package _2_2��ʼ�߳�_�̵߳Ļ�������._2_2_3�߳��ж�;

/**
 * 
 * @author admin��Wu_Being�� Date&Time��2016-4-24 ����12:43:01����
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
		Thread.sleep(2000);// Thread.sleep()�Ǿ�̬������Ҳ��t1.sleep();
		t1.interrupt();
	}
}
