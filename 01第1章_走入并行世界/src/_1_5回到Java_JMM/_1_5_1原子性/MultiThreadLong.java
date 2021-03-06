
package _1_5回到Java_JMM._1_5_1原子性;

/**
 * @author admin：Wu_Being Date&Time：2016-4-22 下午01:03:06
 */
public class MultiThreadLong {

	public static long t = 0;

	public static class ChangeT implements Runnable {

		private long to;

		public ChangeT(long to) {
			this.to = to;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				MultiThreadLong.t = to;
				Thread.yield();// 线程就绪
			}
		}

	}

	public static class ReadT implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				long tmp = MultiThreadLong.t;
				if (tmp != 111L && tmp != -999L && tmp != 333L && tmp != -444L) 
				{
					System.out.println(tmp);
				}
				Thread.yield();
			}
		}

	}

	public static void main(String[] args) {

		new Thread(new ChangeT(111L)).start();
		new Thread(new ChangeT(-999L)).start();
		new Thread(new ChangeT(333L)).start();
		new Thread(new ChangeT(-444L)).start();
		new Thread(new ReadT()).start();
	}
}

/**
 * 4294966297
4294966297
4294966297
4294966297
4294966297
-4294967185
-4294966963
4294966852
-4294966963
4294966297
-4294967185
-4294966963
4294966852
4294966297
4294966297
-4294966963
4294966297
-4294966963
-4294966963
-4294966963
4294966297
4294966852
4294966297
4294966852
4294966297
-4294967185
4294966852
4294966852
4294966297
4294966297
-4294967185
-4294966963
-4294966963

 */
/**
 * http://wangleide414.iteye.com/blog/1669479
 * 
 * 方法 方法说明 sleep() 允许指定以毫秒为单位的一段时间作为参数，它使得线程在指定的时间 内进入阻塞状态，不能得到CPU
 * 时间，指定的时间一过，线程重新进入可执行状态
 * 
 * suspend()使得线程进入阻塞状态，并且不会自动恢复，必须其对应的resume() 被调用，才能使得线程重新进入可执行状态。 resume()
 * 对调用过suspend()的线程调用resume()，可使其重新进去可执行状态
 * 
 * yield() 使得线程放弃当前分得的 CPU 时间，但是不使线程阻塞，即线程仍处于可执行状态，随时可能再次分得 CPU 时间
 * 
 * wait() 使得线程进入阻塞状态，它有两种形式，一种允许指定以毫秒为单位的一段时间作为参数，另一种没有参数 notify()
 * 有参数的notify()调用当对应的 notify() 被调用或者超出指定时间时线程重新进入可执行状态，没有参数的notify()调用则必须对应的
 * notify() 被调用。
 * 
 * 
 */
