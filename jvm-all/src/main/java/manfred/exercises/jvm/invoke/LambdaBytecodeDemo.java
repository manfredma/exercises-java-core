package manfred.exercises.jvm.invoke;

import java.util.stream.Stream;

/**
 * 演示 Lambda 表达式在 JVM 层面通过 invokedynamic 指令实现的基础示例。
 *
 * 使用 Stream API 和 Lambda 过滤字符串，编译后字节码中会生成 invokedynamic 指令，
 * 配合 javap -p -v 可观察到 Lambda 对应的合成方法和引导方法调用，
 * 用于理解 Java 8 Lambda 实现机制与传统匿名内部类的字节码差异。
 */
public class LambdaBytecodeDemo {

    public static void main(String[] args) {
        long lengthyColors = Stream.of("Red", "Green", "Blue").filter(c -> c.length() > 3).count();

    }
}
