package manfred.exercises.lang.basic.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author 可重复注解的容器注解。
 *
 * Java 8 可重复注解机制要求定义一个容器注解来持有多个 @Author 实例，
 * 通过 value() 方法返回 Author 数组，使同一元素上可多次标注 @Author。
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Authors {

    Author[] value();

}
