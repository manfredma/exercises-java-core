package manfred.exercises.io.nio;

import java.nio.ByteBuffer;

/**
 * 演示 NIO ByteBuffer 的核心状态变化。
 *
 * 通过 {@code allocate}、{@code put} 和 {@code flip} 三个操作，
 * 逐步打印 ByteBuffer 的 position、limit、capacity 属性值，直观展示
 * "写模式"切换到"读模式"时各指针的变化规律，帮助理解 ByteBuffer 双模式设计。
 */
public class ByteBufferDemo {

    public static void main(String[] args) throws Exception {
        ByteBuffer bb = ByteBuffer.allocate(10000);
        System.out.println("** init: ");
        System.out.println("array: " + new String(bb.array()) + ", limit:" + bb.limit() + ", capacity:" + bb.capacity() + ", position:" + bb.position());

        bb.put("hello, world".getBytes());
        System.out.println("** after put: hello, world");
        System.out.println("array: " + new String(bb.array()) + ", limit:" + bb.limit() + ", capacity:" + bb.capacity() + ", position:" + bb.position());

        bb.flip();
        System.out.println("** after flip: ");
        System.out.println("array: " + new String(bb.array()) + ", limit:" + bb.limit() + ", capacity:" + bb.capacity() + ", position:" + bb.position());
    }
}
