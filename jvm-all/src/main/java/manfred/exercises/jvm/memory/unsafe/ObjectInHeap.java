package manfred.exercises.jvm.memory.unsafe;

import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
/**
 * 演示使用 Unsafe.allocateMemory 直接在 JVM 管理范围之外分配原生内存。
 *
 * 每次构造 ObjectInHeap 对象时通过 Unsafe 分配 200MB 的本地内存，
 * 该内存不受 JVM GC 管理，仅当对象被显式 freeMemory 或进程退出时才会释放，
 * 用于理解 Unsafe 直接内存操作的特性以及未释放内存导致本地内存泄漏的风险。
 */
public class ObjectInHeap {
    private long address = 0;

    private Unsafe unsafe = GetUnsafeInstance.getUnsafeInstance();

    public ObjectInHeap() {
        address = unsafe.allocateMemory(200 * 1024 * 1024);
    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            ObjectInHeap heap = new ObjectInHeap();
            System.out.println("memory address=" + heap.address);
			TimeUnit.MILLISECONDS.sleep(100L);
        }
    }
}