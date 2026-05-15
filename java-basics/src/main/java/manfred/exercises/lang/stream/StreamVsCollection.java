package manfred.exercises.lang.stream;

import java.util.*;
import java.util.stream.*;

/**
 * 演示 Stream 与 Collection 的核心区别：流只能被消费一次。
 *
 * 对同一个 Stream 调用两次 forEach 会抛出 IllegalStateException，
 * 说明流是惰性求值的一次性数据管道而非可反复遍历的数据结构。
 */
public class StreamVsCollection {

    public static void main(String...args){
        List<String> names = Arrays.asList("Java8", "Lambdas", "In", "Action");
        Stream<String> s = names.stream();
        s.forEach(System.out::println);
        // uncommenting this line will result in an IllegalStateException
        // because streams can be consumed only once
        //s.forEach(System.out::println);
    }
}