package manfred.exercises.lang.basic;

/**
 * 演示 Java 中常用的随机数生成方式。
 *
 * <p>知识点：
 * <ul>
 *   <li>{@link java.util.Random} 是伪随机数生成器（PRNG），基于线性同余算法，相同种子产生相同序列</li>
 *   <li>{@code Math.random()} 内部使用全局共享的 {@code Random} 实例，返回 {@code [0.0, 1.0)} 之间的 double</li>
 *   <li>Java 7+ 引入 {@link java.util.concurrent.ThreadLocalRandom}，多线程场景下性能优于 {@code Random}（无锁竞争）</li>
 *   <li>安全敏感场景（如生成令牌、密码盐）应使用 {@link java.security.SecureRandom}，代价是性能较低</li>
 *   <li>Java 17+ 提供 {@code RandomGenerator} 接口及多种算法实现（如 {@code Xoshiro256PlusPlus}），可通过 {@code RandomGeneratorFactory} 选择</li>
 * </ul>
 */
public class RandomDemo {
    public static void main(String[] args) {

    }
}
