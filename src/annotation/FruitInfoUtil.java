package annotation;

import java.lang.reflect.Field;

/**
 * @author number47
 * @date 2020/6/5 22:37
 * @description
 */
public class FruitInfoUtil {

	public static void getFruitInfo(Class<?> clazz){
		String strFruitProvider = "供应商信息";
		//通过反射获取处理注解
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(FruitProvider.class)) {
				FruitProvider fruitProvider = (FruitProvider) field.getAnnotation(FruitProvider.class);
				//注解信息的处理地方
				strFruitProvider = " 供应商编号：" + fruitProvider.id() + " 供应商名称："
						+ fruitProvider.name() + " 供应商地址："+ fruitProvider.address();
				System.out.println(strFruitProvider);
			}
		}
	}
}
