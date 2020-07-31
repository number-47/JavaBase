package thread.security;

/**
 * @author number47
 * @date 2020/6/16 13:16
 * @description 乘客
 */
public class Passenger extends Thread{

	public Passenger(Runnable target,String name){
		super(target,name);
	}
}
