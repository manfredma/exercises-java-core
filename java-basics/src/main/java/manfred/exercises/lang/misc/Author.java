package manfred.exercises.lang.misc;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 可重复注解 @Author，用于标注代码作者信息。
 *
 * 配合容器注解 @Authors 实现 Java 8 引入的可重复注解机制，
 * 通过 @Repeatable 声明关联容器类型，保留策略为 RUNTIME，
 * 配合 Book 类演示通过 getAnnotationsByType 获取多个重复注解实例的用法。
 */
@Repeatable(Authors.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Author {

    String name();

}