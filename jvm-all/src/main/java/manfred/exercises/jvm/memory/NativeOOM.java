package manfred.exercises.jvm.memory;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 演示通过 ByteBuffer.allocateDirect 持续分配堆外内存导致本地内存 OOM。
 *
 * 将每次分配的 DirectByteBuffer 引用保存在列表中，阻止 GC 回收 DirectByteBuffer
 * 对象从而触发本地内存的释放，直到超出 MaxDirectMemorySize 限制抛出
 * OutOfMemoryError: Direct buffer memory，用于理解直接内存的分配和 GC 回收机制。
 */
public class NativeOOM {
    public static void main(String[] args) {
        List<ByteBuffer> holder = new ArrayList<>();
        while (true) {
            holder.add(ByteBuffer.allocateDirect(1024 * 1024 * 1024));
        }
    }
}
