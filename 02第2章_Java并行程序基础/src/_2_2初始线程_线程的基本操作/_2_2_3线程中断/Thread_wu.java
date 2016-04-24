package _2_2初始线程_线程的基本操作._2_2_3线程中断;

/**
 * 
 * @author admin：Wu_Being；
 * Date&Time：2016-4-24 下午12:58:22；；
 */
public class Thread_wu {
	public void interrupt() {
		//中断线程，设置中断tag+
	}

	public boolean isInterrupted() {
		return false;
		//判断是否中断（tag+）
	}

	public static boolean interrupted() {
		return false;
		//判断是否则中断，并清除当前中断状态
	}
}
