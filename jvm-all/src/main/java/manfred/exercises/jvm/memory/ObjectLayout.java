package manfred.exercises.jvm.memory;

import manfred.exercises.jvm.memory.model.SampleDataObject;
import static java.lang.System.out;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

/**
 * 使用 JOL 工具打印对象内存布局，演示对象头、字段对齐和指针压缩的效果。
 *
 * 通过 ClassLayout.parseInstance 打印 String、自定义类 SampleDataObject 及各类型包装对象的
 * 内存结构（对象头大小、字段偏移量、padding 字节），并通过 VM.current().addressOf()
 * 展示对象的实际堆地址，用于深入理解 JVM 对象内存模型和压缩指针（CompressedOops）机制。
 */
public class ObjectLayout {

    public static void main(String[] args) {
        out.println(VM.current().details());
        String ins = "哈哈哈哈哈哈哈哈";
        out.println(ClassLayout.parseInstance(String.class).toPrintable());
        out.println(ClassLayout.parseInstance(SampleDataObject.class).toPrintable());
        out.println(ClassLayout.parseInstance(ins).toPrintable());

        SampleDataObject x = new SampleDataObject();
        out.println(ClassLayout.parseInstance(x).toPrintable());
        out.println("current address of String.class: " + VM.current().addressOf(String.class));
        out.println("current address of SampleDataObject.class: " + VM.current().addressOf(SampleDataObject.class));
        out.println("hash is " + Integer.toHexString(System.identityHashCode(x)));
        out.println(ClassLayout.parseInstance(x).toPrintable());

        out.println(ClassLayout.parseInstance(new int[40]).toPrintable());
        out.println(ClassLayout.parseInstance(new Object[40]).toPrintable());
    }
}

