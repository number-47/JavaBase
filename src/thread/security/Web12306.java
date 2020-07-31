package thread.security;

/**
 * @author number47
 * @date 2020/6/16 13:18
 * @description
 */
public class Web12306 implements Runnable {
	private String name;
	private int available;

	public Web12306(String name, int available) {
		this.name = name;
		this.available = available;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (Web12306.class){
				if (available > 0) {
					System.out.println(Thread.currentThread().getName() + "买到第" + available + "张票");
					available -= 1;
				} else {
					break;
				}
			}

		}
	}
}
