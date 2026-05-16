package manfred.exercises.jvm.memory;

import java.nio.ByteBuffer;

/**
 * -Xmx64m
 * -Xms64m
 * -Xmn32m
 * -XX:+UseConcMarkSweepGC
 * -XX:+PrintGCDetails
 * -XX:+DisableExplicitGC//限制GC限制调用
 * -XX:MaxDirectMemorySize=10M//堆外10M
 * <p>
 * native memory满了，但是young区没满，没有发生young gc回收DirectByteBuffer，
 * 所以堆外OOM（如果去掉DisableExplicitGC参数程序会一直有Full GC的信息输出，
 * 因为分配native memory的时候会主动调用System.GC()）
 *

 */
public class DirectMemoryOomDemo1 {
    public static void main(String[] args) {
        int i = 1;
        while (true) {
            System.out.println("第" + (i++) + "次");
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024);
        }
    }
}
