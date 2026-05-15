package manfred.exercises.lang.lambda;

import java.util.function.Function;

/**
 * 演示 Lambda 表达式替代匿名内部类实现函数式接口。
 *
 * 以单行 Lambda 表达式实现 Function&lt;Object, String&gt;，
 * 与 InnerClass.java 的匿名内部类写法形成对比，
 * 展示 Lambda 在语法简洁性上的提升。
 */
public class Lambda {
    Function<Object, String> f = obj -> obj.toString();
}