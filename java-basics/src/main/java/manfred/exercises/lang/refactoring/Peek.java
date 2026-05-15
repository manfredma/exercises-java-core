package manfred.exercises.lang.refactoring;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 演示 Stream.peek 在调试流水线中间状态的用法。
 *
 * 在 filter、map、limit 等操作之间插入 peek 打印每个阶段的元素，
 * 无需中断流管道即可观察中间结果，是流操作调试的常用技巧。
 */
public class Peek {

    public static void main(String[] args) {

        List<Integer> result = Stream.of(2, 3, 4, 5, 6, 7, 8, 9, 10)
                .peek(x -> System.out.println(">> taking from stream: " + x)).map(x -> x + 17)
                .peek(x -> System.out.println("after map: " + x)).filter(x -> x % 2 == 0)
                .peek(x -> System.out.println("after filter: " + x)).limit(3)
                .peek(x -> System.out.println("after limit: " + x)).collect(toList());
    }
}