package manfred.exercises.lang.basic.model;

import java.util.Arrays;

/**
 * 演示 Java 8 可重复注解的使用与反射读取。
 *
 * 在同一个类上多次标注 @Author 注解，运行时通过 getAnnotationsByType 方法
 * 获取所有 @Author 实例并逐个输出，验证可重复注解在反射层面的完整支持。
 */
@Author(name = "Raoul")
@Author(name = "Mario")
@Author(name = "Alan")
public class Book {

    public static void main(String[] args) {
        Author[] authors = Book.class.getAnnotationsByType(Author.class);
        Arrays.asList(authors).stream().forEach(a -> {
            System.out.println(a.name());
        });
    }

}
