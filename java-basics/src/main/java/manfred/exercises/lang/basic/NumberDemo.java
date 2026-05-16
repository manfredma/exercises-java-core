package manfred.exercises.lang.basic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示 Java 基本数值类型的边界值，以及 {@link AtomicInteger} 溢出后的环绕行为。
 *
 * <p>知识点：
 * <ul>
 *   <li>{@link Long#MAX_VALUE} = 2<sup>63</sup>−1 = 9223372036854775807（19 位），{@link Integer#MAX_VALUE} = 2<sup>31</sup>−1 = 2147483647（10 位）</li>
 *   <li>Java 整数运算遵循「溢出环绕」（wrap-around）语义：超出最大值后从最小值重新开始，不抛异常</li>
 *   <li>{@code Integer.MAX_VALUE + 1 == Integer.MIN_VALUE}（即 -2147483648），这是二进制补码溢出的直接体现</li>
 *   <li>{@link AtomicInteger} 提供基于 CAS（Compare-And-Swap）的无锁原子操作，适合多线程计数场景</li>
 *   <li>{@code AtomicInteger.incrementAndGet()} 原子地将值加 1 并返回新值，溢出时同样环绕至 {@code MIN_VALUE}</li>
 *   <li>Java 8+ 提供 {@link Math#addExact(int, int)} 等方法，溢出时抛出 {@link ArithmeticException}，适合需要溢出检测的场景</li>
 * </ul>
 */
public class NumberDemo {
    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(String.valueOf(Long.MAX_VALUE).length());
        System.out.println(String.valueOf(Integer.MAX_VALUE).length());
        AtomicInteger atomicInteger = new AtomicInteger(Integer.MAX_VALUE);
        System.out.println(atomicInteger.incrementAndGet());
    }
}
