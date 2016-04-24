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
		volatile boolean stopme = false;//volatile 可去？

		public void stopMe() {
			stopme = true;
		}// ////////////////////////////////////////stopme

		@Override
		public void run() {
			System.out.println(getId() + ":change...");
			// TODO Auto-generated method stub
			// super.run();
			while (true) {
				// //////////////////////////////////////////////stopme
				System.out.println(getId() + ":" + 1 + "--in while "
						+ "##u.id,u.name" + u.getId() + "," + u.getName());//
				if (stopme) {
					System.out.println(getId() + ":exit by stop me!!!!"
							+ "##u.id,u.name" + u.getId() + "," + u.getName());
					break;
				}
				// //////////////////////////////////////////////stopme
				synchronized (u) {
					System.out.println(getId() + ":" + 2 + "--before setId "
							+ "##u.id,u.name" + u.getId() + "," + u.getName());//
					int v = (int) (System.currentTimeMillis() / 1000);
					u.setId(v);
					System.out.println(getId() + ": " + 3 + "--after setId"
							+ "##u.id,u.name" + u.getId() + "," + u.getName());//
					try {
						Thread.sleep(400);
					} catch (InterruptedException e) {
					}
					System.out.println(getId() + ":" + 4 + "--before setName "
							+ "##u.id,u.name" + u.getId() + "," + u.getName());//
					u.setName(String.valueOf(v));
					System.out.println(getId() + ":" + 5 + "--after setName"
							+ "##u.id,u.name" + u.getId() + "," + u.getName());//

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
			System.out.println(getId() + ":" + "read...");
			while (true) {
				System.out.println(getId() + ":" + "read..in.");
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
			System.out.println("main...2." + tn.getId());
			tn.start();// ->run()...
			Thread.sleep(800);
			tn.stopMe();//#######从10.4继续，->10.5->10.1->stopme !!!!!!!使得修改一致
			System.out.println("main...2stopMe." + tn.getId());

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
 * main...1
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
User [id=0,name=0]
8:read..in.
User [id=0,name=0]
8:read..in.
main...2.9
User [id=0,name=0]
8:read..in.
9:change...
9:1--in while ##u.id,u.name0,0
User [id=0,name=0]
8:read..in.
9:2--before setId ##u.id,u.name0,0
9: 3--after setId##u.id,u.name1461465683,0
9:4--before setName ##u.id,u.name1461465683,0
9:5--after setName##u.id,u.name1461465683,1461465683
User [id=1461465683,name=1461465683]
9:1--in while ##u.id,u.name1461465683,1461465683
8:read..in.
User [id=1461465683,name=1461465683]
9:2--before setId ##u.id,u.name1461465683,1461465683
9: 3--after setId##u.id,u.name1461465683,1461465683
8:read..in.
main...2stopMe.9
main...2
main...2.10													#######
10:change...												#######
10:1--in while ##u.id,u.name1461465683,1461465683
9:4--before setName ##u.id,u.name1461465683,1461465683
9:5--after setName##u.id,u.name1461465683,1461465683
9:1--in while ##u.id,u.name1461465683,1461465683
10:2--before setId ##u.id,u.name1461465683,1461465683
9:exit by stop me!!!!##u.id,u.name1461465683,1461465683
10: 3--after setId##u.id,u.name1461465683,1461465683
10:4--before setName ##u.id,u.name1461465683,1461465683
10:5--after setName##u.id,u.name1461465683,1461465683
User [id=1461465683,name=1461465683]
10:1--in while ##u.id,u.name1461465683,1461465683
10:2--before setId ##u.id,u.name1461465683,1461465683
10: 3--after setId##u.id,u.name1461465684,1461465683		#######
8:read..in.
main...2stopMe.10											#######
main...2
main...2.11
11:change...
11:1--in while ##u.id,u.name1461465684,1461465683
10:4--before setName ##u.id,u.name1461465684,1461465683		#######从10.4继续
10:5--after setName##u.id,u.name1461465684,1461465684		#######
11:2--before setId ##u.id,u.name1461465684,1461465684
11: 3--after setId##u.id,u.name1461465684,1461465684
10:1--in while ##u.id,u.name1461465684,1461465684
10:exit by stop me!!!!##u.id,u.name1461465684,1461465684	#######
11:4--before setName ##u.id,u.name1461465684,1461465684
11:5--after setName##u.id,u.name1461465684,1461465684
User [id=1461465684,name=1461465684]
8:read..in.
11:1--in while ##u.id,u.name1461465684,1461465684
User [id=1461465684,name=1461465684]
11:2--before setId ##u.id,u.name1461465684,1461465684
8:read..in.
11: 3--after setId##u.id,u.name1461465685,1461465684
main...2stopMe.11
main...2
main...2.12
12:change...
12:1--in while ##u.id,u.name1461465685,1461465684
11:4--before setName ##u.id,u.name1461465685,1461465684
11:5--after setName##u.id,u.name1461465685,1461465685
12:2--before setId ##u.id,u.name1461465685,1461465685
12: 3--after setId##u.id,u.name1461465685,1461465685
11:1--in while ##u.id,u.name1461465685,1461465685
11:exit by stop me!!!!##u.id,u.name1461465685,1461465685
12:4--before setName ##u.id,u.name1461465685,1461465685
12:5--after setName##u.id,u.name1461465685,1461465685
User [id=1461465685,name=1461465685]
12:1--in while ##u.id,u.name1461465685,1461465685
12:2--before setId ##u.id,u.name1461465685,1461465685
12: 3--after setId##u.id,u.name1461465685,1461465685
8:read..in.
main...2stopMe.12
main...2
main...2.13
12:4--before setName ##u.id,u.name1461465685,1461465685
12:5--after setName##u.id,u.name1461465685,1461465685
User [id=1461465685,name=1461465685]
8:read..in.
User [id=1461465685,name=1461465685]
13:change...
13:1--in while ##u.id,u.name1461465685,1461465685
13:2--before setId ##u.id,u.name1461465685,1461465685
13: 3--after setId##u.id,u.name1461465686,1461465685
12:1--in while ##u.id,u.name1461465685,1461465685
12:exit by stop me!!!!##u.id,u.name1461465686,1461465685
8:read..in.
13:4--before setName ##u.id,u.name1461465686,1461465685
13:5--after setName##u.id,u.name1461465686,1461465686
User [id=1461465686,name=1461465686]
13:1--in while ##u.id,u.name1461465686,1461465686
13:2--before setId ##u.id,u.name1461465686,1461465686
13: 3--after setId##u.id,u.name1461465686,1461465686
8:read..in.
main...2stopMe.13
main...2
main...2.14
14:change...
14:1--in while ##u.id,u.name1461465686,1461465686
13:4--before setName ##u.id,u.name1461465686,1461465686
13:5--after setName##u.id,u.name1461465686,1461465686
14:2--before setId ##u.id,u.name1461465686,1461465686
14: 3--after setId##u.id,u.name1461465687,1461465686
13:1--in while ##u.id,u.name1461465687,1461465686
13:exit by stop me!!!!##u.id,u.name1461465687,1461465686
14:4--before setName ##u.id,u.name1461465687,1461465686
14:5--after setName##u.id,u.name1461465687,1461465687
User [id=1461465687,name=1461465687]
14:1--in while ##u.id,u.name1461465687,1461465687
8:read..in.
14:2--before setId ##u.id,u.name1461465687,1461465687
14: 3--after setId##u.id,u.name1461465687,1461465687
main...2stopMe.14
main...2
main...2.15
15:change...
15:1--in while ##u.id,u.name1461465687,1461465687
14:4--before setName ##u.id,u.name1461465687,1461465687
14:5--after setName##u.id,u.name1461465687,1461465687
15:2--before setId ##u.id,u.name1461465687,1461465687
15: 3--after setId##u.id,u.name1461465687,1461465687
14:1--in while ##u.id,u.name1461465687,1461465687
14:exit by stop me!!!!##u.id,u.name1461465687,1461465687
15:4--before setName ##u.id,u.name1461465687,1461465687
15:5--after setName##u.id,u.name1461465687,1461465687
User [id=1461465687,name=1461465687]
15:1--in while ##u.id,u.name1461465687,1461465687
15:2--before setId ##u.id,u.name1461465687,1461465687
15: 3--after setId##u.id,u.name1461465688,1461465687
8:read..in.
main...2stopMe.15
main...2
main...2.16
16:change...
16:1--in while ##u.id,u.name1461465688,1461465687
15:4--before setName ##u.id,u.name1461465688,1461465687
15:5--after setName##u.id,u.name1461465688,1461465688
16:2--before setId ##u.id,u.name1461465688,1461465688
16: 3--after setId##u.id,u.name1461465688,1461465688
15:1--in while ##u.id,u.name1461465688,1461465688
15:exit by stop me!!!!##u.id,u.name1461465688,1461465688
16:4--before setName ##u.id,u.name1461465688,1461465688
16:5--after setName##u.id,u.name1461465688,1461465688
User [id=1461465688,name=1461465688]
16:1--in while ##u.id,u.name1461465688,1461465688
16:2--before setId ##u.id,u.name1461465688,1461465688
16: 3--after setId##u.id,u.name1461465689,1461465688
8:read..in.
main...2stopMe.16
main...2
main...2.17
17:change...
17:1--in while ##u.id,u.name1461465689,1461465688
16:4--before setName ##u.id,u.name1461465689,1461465688
16:5--after setName##u.id,u.name1461465689,1461465689
17:2--before setId ##u.id,u.name1461465689,1461465689
17: 3--after setId##u.id,u.name1461465689,1461465689
16:1--in while ##u.id,u.name1461465689,1461465689
16:exit by stop me!!!!##u.id,u.name1461465689,1461465689
17:4--before setName ##u.id,u.name1461465689,1461465689
17:5--after setName##u.id,u.name1461465689,1461465689
User [id=1461465689,name=1461465689]
8:read..in.
User [id=1461465689,name=1461465689]
17:1--in while ##u.id,u.name1461465689,1461465689
17:2--before setId ##u.id,u.name1461465689,1461465689
17: 3--after setId##u.id,u.name1461465689,1461465689
8:read..in.
main...2stopMe.17
main...2
main...2.18
18:change...
18:1--in while ##u.id,u.name1461465689,1461465689
17:4--before setName ##u.id,u.name1461465689,1461465689
17:5--after setName##u.id,u.name1461465689,1461465689
18:2--before setId ##u.id,u.name1461465689,1461465689
18: 3--after setId##u.id,u.name1461465690,1461465689
17:1--in while ##u.id,u.name1461465690,1461465689
17:exit by stop me!!!!##u.id,u.name1461465690,1461465689
18:4--before setName ##u.id,u.name1461465690,1461465689
18:5--after setName##u.id,u.name1461465690,1461465690
User [id=1461465690,name=1461465690]
18:1--in while ##u.id,u.name1461465690,1461465690
18:2--before setId ##u.id,u.name1461465690,1461465690
18: 3--after setId##u.id,u.name1461465690,1461465690
8:read..in.
main...2stopMe.18
main...2
main...2.19
19:change...
19:1--in while ##u.id,u.name1461465690,1461465690
18:4--before setName ##u.id,u.name1461465690,1461465690
18:5--after setName##u.id,u.name1461465690,1461465690
19:2--before setId ##u.id,u.name1461465690,1461465690
19: 3--after setId##u.id,u.name1461465691,1461465690
18:1--in while ##u.id,u.name1461465691,1461465690
18:exit by stop me!!!!##u.id,u.name1461465691,1461465690
19:4--before setName ##u.id,u.name1461465691,1461465690
19:5--after setName##u.id,u.name1461465691,1461465691
User [id=1461465691,name=1461465691]
19:1--in while ##u.id,u.name1461465691,1461465691
19:2--before setId ##u.id,u.name1461465691,1461465691
19: 3--after setId##u.id,u.name1461465691,1461465691
8:read..in.
main...2stopMe.19
main...2
main...2.20
20:change...
20:1--in while ##u.id,u.name1461465691,1461465691

 * 
 * */
