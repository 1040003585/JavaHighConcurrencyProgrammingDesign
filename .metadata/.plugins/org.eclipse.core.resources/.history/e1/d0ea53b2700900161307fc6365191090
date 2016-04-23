package _2_2初始线程_线程的基本操作._2_2_2终止线程;

import sun.print.resources.serviceui;

/**
 * 
 * @author admin：Wu_Being； Date&Time：2016-4-23 下午10:03:08；；
 */
public class StopThreadUnsafe {

	public static User u = new User();//

	public static class User {
		private int id;
		private String name;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public User() {
			id = 0;
			name = "0";
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			// return super.toString();
			return "User [id=" + id + ",name=" + name + "]";
		}
	}

	/**
	 * 
	 * @author admin：Wu_Being； Date&Time：2016-4-23 下午10:17:01；；
	 */
	public static class ChangeObjectThread extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			// super.run();
			while (true) {
				synchronized (u) {
					int v = (int) (System.currentTimeMillis() / 1000);
					u.setId(v);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					u.setName(String.valueOf(v));

				}
				Thread.yield();
			}

		}
	}

	/**
	 * 
	 * @author admin：Wu_Being； Date&Time：2016-4-23 下午10:17:11；；
	 */
	public static class ReadObjectThread extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			// super.run();
			while (true) {
				synchronized (u) {
					if (u.getId() != Integer.parseInt(u.getName())) {
						System.out.println(u.toString());
					}
				}
				Thread.yield();
			}
		}
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		new ReadObjectThread().start();
		while (true) {
			Thread tn = new ChangeObjectThread();
			tn.start();
			Thread.sleep(10);
			tn.stop();// ///
		}
		/**
		 * 100 150 User [id=1461423393,name=1461423392]
		 * 
		 * User [id=1461423393,name=1461423392]
		 * 
		 * User [id=1461423395,name=1461423394]
		 */

		/**
		 * 100 100 User [id=1461423843,name=0]
		 * 
		 * User [id=1461423844,name=1461423843]...
		 * 
		 * User [id=1461423844,name=1461423843]...
		 * 
		 * User [id=1461423845,name=1461423844]..
		 * 
		 * 
		 */

		/**
		 * 100 10
		 * 
		 * User [id=1461424189,name=0]
		 * 
		 * User [id=1461424190,name=0]......
		 * 
		 * User [id=1461424483,name=1461424482].....
		 */
		// /*
		// * 类型转换
		// */
		// int a = -100;
		// double d = -10.5;
		// String s = "+10000";
		// // to int
		// System.out.println(Integer.parseInt(s));
		// System.out.println(Integer.valueOf(s));
		// System.out.println(Integer.parseInt("-101010", 2));// radix 代表进制的基数
		// System.out.println(Integer.valueOf("+FF", 16));//
		// System.out.println(Integer.parseInt("ff", 16));//
		// System.out.println(Integer.valueOf("0z", 36));//
		// // to double
		// System.out.println(Double.parseDouble(s));
		// System.out.println(Double.valueOf(s));
		// // to string
		// System.out.println(String.valueOf(a));
		// System.out.println(String.valueOf(d));
	}

}
