package manfred.exercises.jvm.memory.address;

/**
 * 用于演示 JVM 对象内存地址观察的简单数据类。
 *
 * 包含一个 long 类型字段，在构造器中初始化为 1，
 * 作为 ObjectsAddressDemo 中观察对象内存地址变化（GC 前后）的目标实例。
 */
public class A {
    private long a;        // not initialized value

    public A() {
        this.a = 1;        // initialization
    }

    public long a() {
        return this.a;
    }
}
