package manfred.exercises.jvm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * JKD版本：jdk1.8.0_111
 * JVM参数：-XX:+UseParallelGC -XX:+UseParallelOldGC -XX:+PrintGC -XX:+PrintGCTimeStamps
 * -XX:+PrintGCDetails -Xms2g -Xmx2g -Xmn1g
 */
/**
 * 演示多次创建大对象并显式调用 System.gc() 时的 GC 行为。
 *
 * 循环分配超过新生代容量的大对象列表，每轮结束后调用 System.gc()，
 * 配合 GC 日志参数（-XX:+PrintGCDetails 等）可观察每次 Full GC 的内存回收情况，
 * 用于理解显式 GC 与大对象生命周期对垃圾收集器的压力影响。
 */
public class SysGc {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            createBigObject(21);
            System.gc();
        }
    }

    private static void createBigObject(int n) {
        List<byte[]> bytesList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            bytesList.add(new byte[1024 * 1024 * 10]);
        }
        if (bytesList.size() < 20) {
            throw new RuntimeException("this is test");
        }
    }
}
