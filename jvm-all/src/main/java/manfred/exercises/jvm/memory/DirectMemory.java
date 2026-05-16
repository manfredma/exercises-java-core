package manfred.exercises.jvm.memory;

import java.nio.ByteBuffer;

/**
 * 堆内内存与堆外直接内存在分配和访问性能上的对比测试。
 *
 * 分别使用 {@code ByteBuffer.allocate} 和 {@code ByteBuffer.allocateDirect} 进行
 * 百万次读写操作与百万次分配操作，通过计时输出对比两种内存在吞吐量和分配开销上的差异，
 * 直观验证直接内存"分配慢、访问快"的特点，为高性能 I/O 场景的内存选型提供参考。
 */
class DirectMemory {

    // 分配堆内存
    public static void bufferAccess() {
        long startTime = System.currentTimeMillis();
        ByteBuffer b = ByteBuffer.allocate(500);
        for (int i = 0; i < 1000000; i++) {
            for (int j = 0; j < 99; j++)
                b.putInt(j);
            b.flip();
            for (int j = 0; j < 99; j++)
                b.getInt();
            b.clear();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("access_nondirect:" + (endTime - startTime));
    }

    // 直接分配内存
    public static void directAccess() {
        long startTime = System.currentTimeMillis();
        ByteBuffer b = ByteBuffer.allocateDirect(500);
        for (int i = 0; i < 1000000; i++) {
            for (int j = 0; j < 99; j++)
                b.putInt(j);
            b.flip();
            for (int j = 0; j < 99; j++)
                b.getInt();
            b.clear();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("access_direct:" + (endTime - startTime));
    }

    public static void bufferAllocate() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            ByteBuffer.allocate(1_000);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("allocate_nondirect:" + (endTime - startTime));
    }

    public static void directAllocate() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            ByteBuffer.allocateDirect(1_000);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("allocate_direct:" + (endTime - startTime));
    }

    public static void main(String args[]) {
        System.out.println("访问性能测试：");
        bufferAccess();
        directAccess();

        System.out.println();

        System.out.println("分配性能测试：");
        bufferAllocate();
        directAllocate();
    }
}