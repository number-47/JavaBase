package proxy.jdkdynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author number47
 * @date 2020/6/3 15:56
 * @description
 */
public class AdminServiceDynamicProxy {

	private Object target;
	private InvocationHandler invocationHandler;

	public AdminServiceDynamicProxy(Object target,InvocationHandler invocationHandler){
		this.target = target;
		this.invocationHandler = invocationHandler;
	}

	public Object getPersonProxy() {
		Object obj = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), invocationHandler);
		return obj;
	}
}
