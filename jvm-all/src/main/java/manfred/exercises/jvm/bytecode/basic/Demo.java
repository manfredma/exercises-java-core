package manfred.exercises.jvm.bytecode.basic;

/**
 * 演示 JVM 字节码中整数常量的编码方式差异。
 *
 * 通过声明不同范围的整型常量（小整数、接近 Short 边界的值、超过 Short 范围的值），
 * 对应字节码分别使用 bipush、sipush、ldc 等指令，用于理解 JVM 如何根据数值大小
 * 选择最优的常量加载指令，加深对字节码指令集的直觉认识。
 */
public class Demo {
    public static void main(String[] args) {
        int a = 100;
        int b = 98;
        int c = 100 + 98;
        int d = 32768;
    }
}
