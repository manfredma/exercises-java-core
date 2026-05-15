package manfred.exercises.jvm.native_;

/**
 * 演示 JNI（Java Native Interface）调用本地动态库方法的基本流程。
 *
 * 声明 native 方法 hello()，通过静态初始化块加载名为 "hello" 的动态链接库，
 * 展示了 Java 代码如何通过 JNI 调用 C/C++ 编写的本地代码，
 * 是理解 JVM 与操作系统本地代码交互机制的最小示例。
 */
public class HelloWorld {
    public native void hello();

    static {
        //加载动态库的名称
        System.loadLibrary("hello");
    }

    public static void main(String[] args) {
        new HelloWorld().hello();
    }
}
