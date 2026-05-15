package manfred.exercises.jvm.gc;

/**
 * JKD版本：jdk1.8.0_111
 * JVM参数：-XX:+UseParallelGC -XX:+UseParallelOldGC -XX:MetaspaceSize=256k
 */
/**
 * 演示 JVM Metaspace 触发 GC 的场景。
 *
 * 通过设置极小的 MetaspaceSize（-XX:MetaspaceSize=256k）使 JVM 在启动阶段
 * 加载系统类时即触发元空间 GC，配合 GC 日志参数可观察 Metadata GC 的触发条件
 * 和元空间动态扩容行为，用于理解 JDK 8 之后 Metaspace 替代 PermGen 的机制。
 */
public class MetadataGc {
    public static void main(String[] args) {
        System.out.println("hello world");
    }

}