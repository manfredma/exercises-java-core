package manfred.exercises.lang.lambda.model;

import manfred.exercises.lang.lambda.model.Lambda;
import java.util.function.Function;

/**
 * 演示匿名内部类实现函数式接口的传统写法。
 *
 * 以匿名内部类形式实现 Function&lt;Object, String&gt;，
 * 与 Lambda.java 中的 Lambda 表达式写法形成对比，
 * 直观展示 Lambda 相比匿名内部类在语法上的简洁性。
 */
public class InnerClass {
    Function<Object, String> f = new Function<Object, String>() {
        @Override
        public String apply(Object obj) {
            return obj.toString();
        }
    };
}