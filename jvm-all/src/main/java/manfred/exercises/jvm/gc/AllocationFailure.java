package manfred.exercises.jvm.gc;

/**
 * JDK版本：jdk1.8.0_111
 * JVM参数：-Xms200m -Xmx200m -Xmn32m -XX:+UseParallelGC -XX:+UseParallelOldGC
 */
/**
 * 演示大对象分配导致 JVM 内存分配失败（Allocation Failure）的触发场景。
 *
 * 分配超过新生代容量的大对象，迫使 JVM 直接将其放入老年代，当老年代也不足时
 * 触发 Full GC 或 OutOfMemoryError，用于理解大对象分配策略和 GC 触发时机。
 * 配合 JVM 参数 -Xms200m -Xmx200m -Xmn32m -XX:+UseParallelGC 运行观察。
 */
public class AllocationFailure {
    public static void main(String[] args) {
        // 设置大对象，新生代内存有32m，不够
        byte[] bigObj1 = new byte[1024 * 1024 * 160];
        byte[] bigObj2 = new byte[1024 * 1024 * 70];
    }
}