package manfred.exercises.lang.optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.util.Optional;

/**
 * 演示 Optional 的组合操作与 flatMap/map 的嵌套使用。
 *
 * 通过 max 方法展示两个 Optional<Integer> 之间用 flatMap + map 求最大值，
 * 避免显式判空，体现 Optional 在处理可能缺失值时的函数式链式调用风格。
 */
public class OperationsWithOptional {

    public static void main(String... args) {
        System.out.println(max(of(3), of(5)));
        System.out.println(max(empty(), of(5)));

        // 下面的逻辑中用的API是 Java9 中提供的，在 Java8 中无法编译
//        Optional<Integer> opt1 = of(5);
//        Optional<Integer> opt2 = opt1.or(() -> of(4));
//
//        System.out.println(of(5).or(() -> of(4)));
    }

    public static final Optional<Integer> max(Optional<Integer> i, Optional<Integer> j) {
        return i.flatMap(a -> j.map(b -> Math.max(a, b)));
    }
}