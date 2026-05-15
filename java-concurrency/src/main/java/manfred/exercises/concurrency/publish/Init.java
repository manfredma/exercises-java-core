package manfred.exercises.concurrency.publish;

/**
 * 演示对象引用的不安全发布容器类。
 *
 * 持有公开的 Holder 字段并提供 initialize 方法不断替换新实例，
 * 在缺乏同步的情况下，其他线程可能观察到旧引用或部分初始化的 Holder 对象，
 * 用于配合 ConcurrentCollectionDemo 复现 Java 内存模型中的不安全发布问题。
 */
public class Init {
    public Holder holder = new Holder(42);

    public void initialize() {
        holder = new Holder(42);
    }
}
