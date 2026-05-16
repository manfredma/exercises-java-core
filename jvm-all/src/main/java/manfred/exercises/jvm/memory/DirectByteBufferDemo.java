package manfred.exercises.jvm.memory;

import java.nio.ByteBuffer;

/**
 * 演示堆外直接内存 DirectByteBuffer 的各项操作特性。
 *
 * 通过四个独立方法分别展示：mark/reset/rewind 指针控制（{@code testProperties}）、
 * 写入字节数组与字符串后缓冲区状态变化（{@code testWriteRead}）、flip 后按数组
 * 批量读取数据（{@code testReadToArray}）、以及 compact 压缩未读数据的行为
 * （{@code testCompact}），帮助理解 DirectByteBuffer 相对于堆内 ByteBuffer
 * 的使用方式差异。
 */
public class DirectByteBufferDemo {

    public static void main(String[] args) throws Exception {
        DirectByteBufferDemo t = new DirectByteBufferDemo();
        t.testProperties();
        t.testWriteRead();
        t.testReadToArray();
        t.testCompact();
    }

    public void testProperties() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        buffer.putChar('a');
        buffer.putChar('c');
        System.out.println("插入完数据 " + buffer);
        // 记录mark的位置
        buffer.mark();
        // 设置的position一定要比mark大，否则mark无法重置
        buffer.position(30);
        System.out.println("reset前 " + buffer);
        // 重置reset ，reset后的position=mark
        buffer.reset();
        System.out.println("reset后 " + buffer);
        //清除标记，position变成0，mark变成-1
        buffer.rewind();
        System.out.println("清除标记后 " + buffer);
        System.out.println("getChar(1) = " + buffer.getChar() + "; " + buffer);
        System.out.println("getChar(2) = " + buffer.getChar() + "; " + buffer);
    }

    public void testWriteRead() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(10);
        System.out.println("初始化后 " + buffer);
        byte[] data = {1, 2};
        buffer.put(data);
        System.out.println("写byte[]后 " + buffer);
        buffer.clear();//清空了
        //5个byte
        buffer.put("hello".getBytes());
        System.out.println("hello".getBytes().length);
        System.out.println("写string后 " + buffer);
    }

    public void testReadToArray() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(10);
        buffer.put(new byte[]{1, 2, 3, 4});
        System.out.println("刚写完数据 " + buffer);
        buffer.flip();
        System.out.println("flip之后 " + buffer);
        byte[] target = new byte[buffer.limit()];
        //自动读取target.length个数据
        buffer.get(target);
        for (byte b : target) {
            System.out.println(b);
        }
        System.out.println("读取完数组 " + buffer);
    }

    public void testCompact() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(10);
        buffer.put(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        System.out.println("刚写完数据 " + buffer);
        buffer.position(2);
        System.out.println("position之后 " + buffer);
        buffer.compact();
        System.out.println("compact之后 " + buffer);
        buffer.rewind();
        byte[] target = new byte[buffer.limit()];
        //自动读取target.length个数据
        buffer.get(target);
        for (byte b : target) {
            System.out.print(b + " ");
        }
        System.out.println("读取完数组 " + buffer);
    }
}
