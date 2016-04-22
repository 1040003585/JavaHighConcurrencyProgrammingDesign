package _2_2初始线程_线程的基本操作._2_2_1新建线程;

/**
 * 
 * @author admin：Wu_Being；
 * Date&Time：2016-4-22 下午11:55:19；；
 */
public class CreateThread_WithInterface2 {

	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("I am Implemented...");
			}

		});
		thread.start();
	}
}
