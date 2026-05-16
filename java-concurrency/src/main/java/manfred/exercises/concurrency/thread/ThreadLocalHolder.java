package manfred.exercises.concurrency.thread;

/**
 * 演示 ThreadLocal 的公有静态字段持有方式。
 *
 * 使用 ThreadLocal.withInitial 惰性初始化每个线程独立的 Object 实例，
 * 公有字段暴露给外部直接访问，适用于线程内共享状态的简单场景。
 */
public class ThreadLocalHolder {
    public static ThreadLocal<Object> x = ThreadLocal.withInitial(() -> new Object());
}
