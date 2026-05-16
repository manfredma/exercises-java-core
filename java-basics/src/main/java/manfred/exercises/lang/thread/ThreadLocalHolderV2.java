package manfred.exercises.lang.thread;

/**
 * 演示 ThreadLocal 的私有静态字段 + 公有访问方法的封装方式。
 *
 * 将 ThreadLocal 设为 private，通过 getObject() 方法统一访问，
 * 相比 ThreadLocalHolder 的公有字段方式更符合封装原则，
 * 适用于需要控制线程局部变量访问边界的生产场景。
 */
public class ThreadLocalHolderV2 {
    private static ThreadLocal<Object> x = ThreadLocal.withInitial(() -> new Object());

    public static Object getObject() {
        return x.get();
    }
}
