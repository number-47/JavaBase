package reflective;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author number47
 * @date 2020/6/5 12:01
 * @description
 */
public class Reflective {

	public static void main(String[] args) {
		Person person = new Person("小D",21);
		//获取clazz对象的三种方式
		Class personClazz = person.getClass();
		Class clazz = Person.class;
		Class clazzForName = null;
		try {
			clazzForName = Class.forName("reflective.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//获取Person类的所有方法信息
		Method[] methods = clazzForName.getMethods();
		System.out.println("-------获取所有方法信息-------");
		for (Method m: methods) {
			System.out.println(m.toString());
		}

		//获取Person类的所有成员属性信息
		Field[] field = clazzForName.getDeclaredFields();
		System.out.println("-------获取成员属性信息-------");
		try {
			Field field1 = clazz.getDeclaredField("age");
			//设置私有域可访问
			field1.setAccessible(true);
			Object object = field1.get(person);
			System.out.println(field1.toString());
			System.out.println(object.toString());
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println("-------获取所有成员属性信息-------");
		for (Field f: field) {
			System.out.println(f.toString());
		}

		//获取构造函数
		Constructor[] constructors = clazzForName.getConstructors();
		System.out.println("-------获取所有构造函数-------");
		for (Constructor c:	constructors) {
			System.out.println(c.toString());
		}

		//创建对象
		System.out.println("-------创建对象-------");
		Person p = null;
		Person p2 = null;
		try {
			p = (Person)clazzForName.newInstance();
			//获取构造函数
			Constructor constructor = clazzForName.getConstructor(String.class,int.class);
			p2 = (Person)constructor.newInstance("L",14);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		System.out.println(p.toString());
		System.out.println(p2.toString());
	}
}
