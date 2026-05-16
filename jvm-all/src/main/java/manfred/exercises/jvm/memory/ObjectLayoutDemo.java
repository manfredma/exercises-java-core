package manfred.exercises.jvm.memory;

import manfred.exercises.jvm.memory.model.SampleDataObject;
import static java.lang.System.out;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

import java.util.Scanner;

/**
 * 演示使用 JOL（Java Object Layout）工具分析 JVM 中对象的内存布局。
 *
 * <p>知识点：
 * <ul>
 *   <li>JVM 对象头（Object Header）由 Mark Word（8 字节）和 Class Pointer（4/8 字节）组成；数组对象额外包含 4 字节 length 字段</li>
 *   <li>Mark Word 存储 hashCode、GC 分代年龄、锁状态标志（偏向锁/轻量级锁/重量级锁）等元数据</li>
 *   <li>默认开启指针压缩（{@code -XX:+UseCompressedOops}）时，Class Pointer 压缩为 4 字节，引用字段也压缩为 4 字节</li>
 *   <li>对象内存按 8 字节对齐（padding），实际占用 = ceil(header + fields) 向上取整到 8 的倍数</li>
 *   <li>{@link ClassLayout#parseClass(Class)} 分析类的静态布局（不含实例数据）；{@code parseInstance(Object)} 分析具体实例的实际布局</li>
 *   <li>{@link GraphLayout#parseInstance(Object...)} 递归分析对象图，展示对象及其所有可达引用的总内存占用</li>
 *   <li>{@link VM#current()} 输出当前 JVM 的内存模型参数，如地址大小、对齐方式、压缩 OOP 基地址等</li>
 * </ul>
 */
public class ObjectLayoutDemo {

    public static void main(String[] args) {
        out.println(VM.current().details());
        String ins = "哈哈哈哈哈哈哈哈";
        out.println(ClassLayout.parseClass(java.lang.String.class).toPrintable());
        out.println(ClassLayout.parseInstance(ins).toPrintable());

        SampleDataObject x = new SampleDataObject();
        out.println(ClassLayout.parseClass(SampleDataObject.class).toPrintable());
        out.println(ClassLayout.parseInstance(x).toPrintable());
        out.println(ClassLayout.parseInstance(SampleDataObject.class).toPrintable());
        out.println(ClassLayout.parseInstance((short) 2).toPrintable());
        out.println(ClassLayout.parseInstance(2).toPrintable());
        out.println(ClassLayout.parseInstance(2L).toPrintable());
        out.println(ClassLayout.parseInstance(new Object()).toPrintable());
        out.println(ClassLayout.parseInstance(new int[3]).toPrintable());

        out.println(GraphLayout.parseInstance(x).toPrintable());
        out.println(GraphLayout.parseInstance(SampleDataObject.class).toPrintable());
        int[] ia = new int[40];
        byte[] ba = new byte[40];
        boolean[] bla = new boolean[40];
        out.println(ClassLayout.parseInstance(new int[40]).toPrintable());
        out.println(GraphLayout.parseInstance((Object) ia).toPrintable());
        out.println(GraphLayout.parseInstance((Object) ba).toPrintable());
        out.println(GraphLayout.parseInstance((Object) bla).toPrintable());
        out.println("请输入任意字符结束程序...");
        new Scanner(System.in).next();
    }

}

