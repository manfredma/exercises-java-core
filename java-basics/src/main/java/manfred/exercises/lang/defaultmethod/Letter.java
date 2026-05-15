package manfred.exercises.lang.defaultmethod;

import java.util.function.Function;

/**
 * 演示 Function 接口的 andThen 方法构建函数处理管道。
 *
 * 定义添加信头、添加信尾、检查拼写三个静态方法，
 * 通过 Function.andThen 将它们串联成一个处理流水线，
 * 体现函数式接口 default 方法在函数组合（Function Composition）中的实际应用。
 */
public class Letter{
    public static String addHeader(String text){
        return "From Raoul, Mario and Alan:" + text;
    }

    public static String addFooter(String text){
        return text + "Kind regards";
    }

    public static String checkSpelling(String text){
        return text.replaceAll("C\\+\\+", "**Censored**");
    }

    public static void main(String...args){
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline
          = addHeader.andThen(Letter::checkSpelling)
                     .andThen(Letter::addFooter);

        System.out.println(transformationPipeline.apply("C++ stay away from me!"));
    }

}