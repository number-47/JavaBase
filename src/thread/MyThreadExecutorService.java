package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author number47
 * @date 2020/6/3 11:04
 * @description
 */
public class MyThreadExecutorService {

	public static void newFixedThreadPool(){
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 5; i++) {
			threadPool.execute(new MyThreadRunnable());
			System.out.println("线程被调用了" + i);
		}
	}

	public static void newCachedThreadPool(){
		ExecutorService threadPool2 = Executors.newCachedThreadPool();
		List<Future<String>> futures = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Future<String> future = threadPool2.submit(new MyThreadCallable(1));
		}
	}

		public static void newScheduledThreadPool(){
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
		//延迟3秒
//		scheduledThreadPool.schedule(new MyThreadRunnable(),3,TimeUnit.SECONDS);
		//延迟2秒后每五秒执行一次
		scheduledThreadPool.scheduleAtFixedRate(new MyThreadRunnable(),2L,5L,TimeUnit.SECONDS);
	}

	/**
	 * newSingleThreadExecutor 只有一个线程
	 */
	public static void newSingleThreadExecutor(){
		ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
	}


	public static void main(String[] args) {

//		newCachedThreadPool();
//		newFixedThreadPool();
		newScheduledThreadPool();


//		ExecutorService threadPool3 = Executors.newSingleThreadExecutor();
//		ExecutorService threadPool4 = Executors.newScheduledThreadPool(3);

	}
}
