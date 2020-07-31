package ioc.annoation;

import java.lang.annotation.*;

/**
* @Description:
* @Author:         自定义AutoWired
* @CreateDate:
*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
@Documented
public @interface AutoWired {
}
