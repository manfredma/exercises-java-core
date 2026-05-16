package manfred.exercises.lang.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@FunctionalInterface
interface Print<T> {
    void print(T x);
}

/**
 * 演示自定义函数式接口与 Stream.collect(Collectors.toMap) 的用法。
 *
 * 定义泛型函数式接口 Print&lt;T&gt; 并通过 Lambda 传入字符串打印逻辑，
 * 同时演示 toMap 收集器将列表转为键值对，
 * 是函数式接口自定义与 Stream 终端操作的基础练习。
 */
public class LambdaBasicDemo {
    public static void PrintString(String s, Print<String> print) {
        print.print(s);
    }

    public static void main(String[] args) {
        //PrintString("test", (x) -> System.out.println(x));

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("1");
        list.stream().collect(Collectors.toMap(a -> "k" + a, a -> "v" + a + Math.random()));
    }
}