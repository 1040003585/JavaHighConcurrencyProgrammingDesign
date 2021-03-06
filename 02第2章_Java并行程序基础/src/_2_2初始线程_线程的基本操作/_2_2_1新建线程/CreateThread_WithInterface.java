package _2_2初始线程_线程的基本操作._2_2_1新建线程;

/**
 * 
 * @author admin：Wu_Being； Date&Time：2016-4-22 下午11:05:03；；
 */
public class CreateThread_WithInterface implements Runnable {

	/**
	 * 实现了Runable接口，并将该实例传入Thread。 
	 * 这样避免重载Thread.run()，单纯使用接口来定义Thread，也是最常用的做法。
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
