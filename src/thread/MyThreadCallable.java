package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author number47
 * @date 2020/6/3 10:29
 * @description
 */
public class MyThreadCallable implements Callable<String> {

	private int id;

	public MyThreadCallable(int id) {
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		System.out.println("call()方法被自动调用！！！    " + Thread.currentThread().getName());
		//该返回结果将被Future的get方法得到
		return "call()方法被自动调用，任务返回的结果是：" + id + "    " + Thread.currentThread().getName();
	}

	public static void main(String[] args) {
		Callable<String> stringCallable = new MyThreadCallable(1);
		FutureTask<String> future = new FutureTask<String>(stringCallable);

		Thread thread = new Thread(future);
		thread.start();
		//需要等待线程执行完
		try {
			System.out.println("获取结果："+future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
