package thread.security;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author number47
 * @date 2020/6/16 13:45
 * @description 测试Lock锁的使用
 */
public class TestTryLock implements Runnable{

	private Lock lock = new ReentrantLock();
	/**
	 * 需要参与同步的方法
	 */
	@Override
	public void run() {
		if (lock.tryLock()){
			try {
				System.out.println(Thread.currentThread().getName() + "获取到了锁");
			}catch (Exception e){
				e.printStackTrace();
			}finally {
				System.out.println(Thread.currentThread().getName() + "释放到了锁");
				lock.unlock();
			}
		}else {
			System.out.println(Thread.currentThread().getName() + "有人占着锁，我就不要了");
		}
	}

	public static void main(String[] args) {
		Integer f1 = 110;
		TestTryLock testTryLock = new TestTryLock();
		Thread thread1 = new Thread(testTryLock,"t1");
		Thread thread2 = new Thread(testTryLock,"t2");
		thread1.start();
		thread2.start();
	}
}
