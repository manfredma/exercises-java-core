package manfred.exercises.lang.basic;

/**
 * 辅助观察 Java 字节码与 Lambda 实现机制的示例类。
 *
 * 包含私有方法调用（invokespecial）、final 公有方法调用（invokevirtual），
 * 以及 Lambda 表达式的创建与调用，
 * 可结合 javap -c 命令查看生成的字节码指令差异。
 */
public class BytecodeDemo {
    private void a() {

    }

    public final void x() {
        a();
    }

    public static void main(String[] args) {
        BytecodeDemo byteCodeTest = new BytecodeDemo();
        byteCodeTest.x();
        Runnable r = () -> System.out.println("hello");
        r.run();
    }
}
