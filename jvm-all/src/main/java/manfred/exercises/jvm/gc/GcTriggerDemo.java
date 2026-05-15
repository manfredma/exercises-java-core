package manfred.exercises.jvm.gc;

import java.io.IOException;
/**
 * 演示显式调用 System.gc() 触发 Full GC 的行为。
 *
 * 调用 System.gc() 后通过 System.in.read() 暂停程序，为使用 jstat、jvisualvm
 * 等工具观察 GC 前后内存变化提供时间窗口，用于理解显式 GC 触发机制。
 */
public class GcTriggerDemo {
    public static void main(String[] args) throws IOException {
        System.gc();
        System.in.read();
    }
}
