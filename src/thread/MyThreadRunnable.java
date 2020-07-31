package thread;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author number47
 * @date 2020/6/2 21:25
 * @description
 */
public class MyThreadRunnable implements Runnable{
	@Override
	public void run() {
		System.out.println("线程创建方式二：实现Runnable接口" + Thread.currentThread().getName() + ":" + System.currentTimeMillis()/1000%60);
	}

	public static void main(String[] args) {
		MyThreadRunnable myThreadRunnable = new MyThreadRunnable();
		//当传入一个Runnable参数时，Thread的run方法会调用MyThreadRunnable的run()方法
		Thread thread = new Thread(myThreadRunnable);
		thread.start();
	}
}
