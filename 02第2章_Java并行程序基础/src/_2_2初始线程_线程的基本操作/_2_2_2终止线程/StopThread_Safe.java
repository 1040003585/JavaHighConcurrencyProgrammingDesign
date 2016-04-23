package _2_2初始线程_线程的基本操作._2_2_2终止线程;

/**
 * 
 * @author admin：Wu_Being； Date&Time：2016-4-23 下午10:03:08；；
 */
public class StopThread_Safe {

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
			return "User [id=" + id + ",name=" + name + "]";
		}
	}

	/**
	 * 
	 * 
	 * @author admin：Wu_Being； Date&Time：2016-4-23 下午10:17:01；；
	 */
	public static class ChangeObjectThread extends Thread {

		// /////////////////////////////////////////stopme
		volatile boolean stopme = false;

		public void stopMe() {
			stopme = true;
		}// ////////////////////////////////////////stopme

		@Override
		public void run() {
			System.out.println(getId()+":change...");
			// TODO Auto-generated method stub
			// super.run();
			while (true) {
				// //////////////////////////////////////////////stopme
				System.out.println(getId()+":"+1);//
				if (stopme) {
					System.out.println(getId()+":exit by stop me");
					break;
				}
				// //////////////////////////////////////////////stopme
				synchronized (u) {
					System.out.println(getId()+":"+2);//
					int v = (int) (System.currentTimeMillis() / 1000);
					u.setId(v);
					System.out.println(getId()+":"+3);//
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
					u.setName(String.valueOf(v));
					System.out.println(getId()+":"+4);//

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
			System.out.println(getId()+":"+"read...");
			while (true) {
				System.out.println(getId()+":"+"read..in.");
					synchronized (u) {
					// if (u.getId() != Integer.parseInt(u.getName()))
					{
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
		System.out.println("main...1");
		while (true) {
			// Thread tn = new ChangeObjectThread();
			// tn.start();
			// Thread.sleep(150);
			// tn.stop();// ///
			// tn.stopMe();//没有stopMe()方法
			System.out.println("main...2");
			ChangeObjectThread tn = new ChangeObjectThread();
			System.out.println("main...2."+tn.getId());
			tn.start();
			Thread.sleep(200);
			tn.stopMe();//
			System.out.println("main...2stopMe."+tn.getId());
			
			/**
			 * ...
			 * 
			 * User [id=1461431323,name=1461431323]
			 * 
			 * User [id=1461431323,name=1461431323]
			 * 
			 * exit by stop me
			 * 
			 * User [id=1461431324,name=1461431324]
			 * 
			 * exit by stop me
			 * 
			 * User [id=1461431324,name=1461431324]...
			 */
		}

	}

}
/**

main...1
main...2
8:read...
8:read..in.
User [id=0,name=0]
8:read..in.
User [id=0,name=0]
8:read..in.
User [id=0,name=0]
8:read..in.
User [id=0,name=0]
8:read..in.
User [id=0,name=0]
8:read..in.
main...2.9
User [id=0,name=0]
9:change...
9:1
9:2
9:3
8:read..in.
9:4
User [id=1461434780,name=1461434780]
8:read..in.
User [id=1461434780,name=1461434780]
9:1
9:2
9:3
8:read..in.
main...2stopMe.9
main...2
main...2.10
10:change...
10:1
9:4
10:2
9:1
9:exit by stop me
10:3
10:4
User [id=1461434781,name=1461434781]
8:read..in.
User [id=1461434781,name=1461434781]
8:read..in.
User [id=1461434781,name=1461434781]
8:read..in.
User [id=1461434781,name=1461434781]
8:read..in.
User [id=1461434781,name=1461434781]
8:read..in.
User [id=1461434781,name=1461434781]
8:read..in.
User [id=1461434781,name=1461434781]
8:read..in.
User [id=1461434781,name=1461434781]
8:read..in.
User [id=1461434781,name=1461434781]
8:read..in.
User [id=1461434781,name=1461434781]
8:read..in.
User [id=1461434781,name=1461434781]
8:read..in.
User [id=1461434781,name=1461434781]
8:read..in.
User [id=1461434781,name=1461434781]
8:read..in.
User [id=1461434781,name=1461434781]
8:read..in.
User [id=1461434781,name=1461434781]
10:1
10:2
10:3
8:read..in.
main...2stopMe.10
main...2
main...2.11
11:change...
11:1
10:4
11:2
11:3
10:1
10:exit by stop me
11:4
User [id=1461434781,name=1461434781]
11:1
11:2
11:3
8:read..in.
main...2stopMe.11
main...2
main...2.12
12:change...
12:1
11:4
12:2
12:3
11:1
11:exit by stop me
12:4
User [id=1461434781,name=1461434781]
12:1
12:2
12:3
8:read..in.
main...2stopMe.12
main...2
main...2.13
13:change...
13:1
12:4
13:2
13:3
12:1
12:exit by stop me
13:4
User [id=1461434781,name=1461434781]
8:read..in.
User [id=1461434781,name=1461434781]
13:1
13:2
13:3
8:read..in.
main...2stopMe.13
main...2
main...2.14
14:change...
14:1
13:4
14:2
14:3
13:1
13:exit by stop me
14:4
User [id=1461434781,name=1461434781]
14:1
14:2
8:read..in.
14:3
main...2stopMe.14
main...2
main...2.15
15:change...
15:1
14:4
15:2
15:3
14:1
14:exit by stop me
15:4
User [id=1461434782,name=1461434782]
15:1
8:read..in.
15:2
15:3
main...2stopMe.15
main...2
main...2.16
16:change...
16:1
15:4
16:2
16:3
15:1
15:exit by stop me
16:4
User [id=1461434782,name=1461434782]
16:1
16:2
16:3
8:read..in.
main...2stopMe.16
main...2
main...2.17
17:change...
17:1
16:4
17:2
17:3
16:1
16:exit by stop me
17:4
User [id=1461434782,name=1461434782]
17:1
17:2
17:3
8:read..in.
main...2stopMe.17
main...2
main...2.18
18:change...
18:1
17:4
18:2
18:3
17:1
17:exit by stop me
18:4
User [id=1461434782,name=1461434782]
18:1
18:2
18:3
8:read..in.
main...2stopMe.18
main...2
main...2.19
19:change...
19:1
18:4
19:2
19:3
18:1
18:exit by stop me
19:4
User [id=1461434782,name=1461434782]
19:1
19:2
19:3
8:read..in.

*/