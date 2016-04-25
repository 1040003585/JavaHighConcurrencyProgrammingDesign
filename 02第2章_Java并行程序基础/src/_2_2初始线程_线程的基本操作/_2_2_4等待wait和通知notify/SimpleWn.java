package _2_2��ʼ�߳�_�̵߳Ļ�������._2_2_4�ȴ�wait��֪ͨnotify;

/**
 * 
 * @author admin��Wu_Being�� Date&Time��2016-4-25 ����12:46:27����
 */
public class SimpleWn {
	final static Object object = new Object();

	public static class T1 extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			// super.run();
			synchronized (object) {// T1���object��
				System.out.println(System.currentTimeMillis() + ":T1 start!");
				try {
					System.out.println(System.currentTimeMillis()
							+ ":T1 wait for object");
					//object.wait(); // T1�ȴ������ͷ�object��
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
			synchronized (object) {// T2���object��
				System.out.println(System.currentTimeMillis()
						+ ":T2 start!notify no thread");
				object.notify();// T2֪ͨT1����ִ�У���T1Ҫ��T2�ͷ�object��������ٻ��object��
				System.out.println(System.currentTimeMillis() + ":T2 end");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
			}// T2�ͷ�object��
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
