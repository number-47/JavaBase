package thread.security;

/**
 * @author number47
 * @date 2020/6/16 13:21
 * @description 产生线程安全的原因：多线程环境下拥有共享数据；
 * 								 多条语句操作共享数据
 */
public class Happy12306 {

	public static void main(String[] args) {
		Runnable web12306 = new Web12306("12306",50);
		new Passenger(web12306,"A").start();
		new Passenger(web12306,"B").start();
		new Passenger(web12306,"C").start();
		new Passenger(web12306,"D").start();
		new Passenger(web12306,"E").start();
	}
}
