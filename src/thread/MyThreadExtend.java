package thread;

/**
 * @author number47
 * @date 2020/6/2 21:00
 * @description 线程创建方式一：继承Thread类
 */
public class MyThreadExtend extends Thread{

	@Override
	public void run(){
		System.out.println("我的线程：线程创建方式一：继承Thread类");
	}

	public static void main(String[] args) {
		MyThreadExtend myThreadExtend = new MyThreadExtend();
		myThreadExtend.start();
	}
}
