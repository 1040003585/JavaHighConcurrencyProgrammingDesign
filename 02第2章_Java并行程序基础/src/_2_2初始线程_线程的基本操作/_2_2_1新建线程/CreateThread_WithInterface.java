package _2_2��ʼ�߳�_�̵߳Ļ�������._2_2_1�½��߳�;

/**
 * 
 * @author admin��Wu_Being�� Date&Time��2016-4-22 ����11:05:03����
 */
public class CreateThread_WithInterface implements Runnable {

	/**
	 * ʵ����Runable�ӿڣ�������ʵ������Thread�� 
	 * ������������Thread.run()������ʹ�ýӿ�������Thread��Ҳ����õ�������
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Thread thread = new Thread(new CreateThread_WithInterface());
		thread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Oh, I am Runable");
	}

//	public interface Runnable {
//		public abstract void run();
//	}

//	public class Thread implements Runnable {
//		public Thread(Runnable target) {
//			init(null, target, "Thread-" + nextThreadNum(), 0);
//		}
//		private Runnable target;
//		@Override
//		public void run() {
//			if (target != null) {
//				target.run();
//			}
//		}
//	}

	
}
