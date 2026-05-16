package manfred.exercises.jvm.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 演示 JVM 堆内存 OOM 异常（java.lang.OutOfMemoryError: Java heap space）的触发。
 *
 * 不断向 List 中追加 100MB 的 byte 数组，直到堆内存耗尽，模拟内存泄漏场景。
 * 配合 -Xmx 限制堆大小运行，可通过 jvisualvm 或 GC 日志观察堆增长和 GC 无法
 * 回收时抛出 OOM 的全过程，加深对堆内存分配与 GC 回收边界的理解。
 */
public class HeapOom {
    public static void main(String[] args) throws Exception {
        List<byte[]> x = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            // TimeUnit.MILLISECONDS.sleep(100);
            x.add(new byte[100 * 1024 * 1024]);
        }
        System.in.read();
    }
}
