package proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author number47
 * @date 2020/6/3 16:09
 * @description
 */
public class AdminServiceCglibProxy implements MethodInterceptor{

	private Object target;

	public AdminServiceCglibProxy(Object target) {
		this.target = target;
	}

	//给目标对象创建一个代理对象
	public Object getProxyInstance() {
		//工具类
		Enhancer en = new Enhancer();
		//设置父类
		en.setSuperclass(target.getClass());
		//设置回调函数
		en.setCallback(this);
		//创建子类代理对象
		return en.create();
	}

	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("判断用户是否有权限进行操作");
		Object obj = method.invoke(target);
		System.out.println("记录用户执行操作的用户信息、更改内容和时间等");
		return obj;
	}
}
