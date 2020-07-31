package ioc;

import ioc.annoation.AutoWired;
import ioc.controller.UserController;
import ioc.service.UserService;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * @author number47
 * @date 2020/6/19 00:39
 * @description
 */
public class MyTest {
	/**
	 * 将userService注入到userController
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		UserController userController = new UserController();
		Class<? extends UserController> clazz = userController.getClass();
		//创建对象
		UserService userService = new UserService();
		//获取所有属性值
		Field serviceField = clazz.getDeclaredField("userService");
		String name = serviceField.getName();
		//拼接方法名称
		name = name.substring(0,1).toUpperCase() + name.substring(1,name.length());
		String serMethodName = "set" + name;
		//通过方法注入属性对象
		Method method = clazz.getMethod(serMethodName,UserService.class);
		//反射
		method.invoke(userController,userService);
		System.out.println(userController.getUserService());
	}

	/**
	 * 通过注解导入Service
	 */
	@Test
	public void test2(){
		UserController userController = new UserController();
		Class<? extends UserController> clazz = userController.getClass();
		//获取所有属性值
		Stream.of(clazz.getDeclaredFields()).forEach(field -> {
			AutoWired annotation = field.getAnnotation(AutoWired.class);
			if (annotation != null){
				field.setAccessible(true);
				//获取属性类型
				Class<?> type = field.getType();
				try {
					Object o = type.newInstance();
					field.set(userController,o);
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println(userController.getUserService());
	}
}
