package manfred.exercises.jvm.memory.init;

/**
 * 演示 JVM 对象初始化阶段 final 字段的赋值时序与"this 逃逸"问题。
 *
 * 构造器中先调用 escapeI()（此时 final 字段 i 尚未赋值，值为默认的 0），
 * 再完成 i 的赋值，展示了 final 字段在构造完成前通过方法调用发生"this 逃逸"
 * 时读取到零值的现象，用于理解 JVM 对象初始化顺序和 final 字段的内存语义。
 */
public class InitOrderDemo {
    final int i;
    int j;
    public InitOrderDemo() {
        escapeI();
        this.j = 4;
        this.i = 3;
    }

    public InitOrderDemo(int i, int j) {
        this.i = i;
        this.j = j;
    }

    private void escapeI() {
        System.out.println(this.i);
    }

    public static void main(String[] args) {
        InitOrderDemo b = new InitOrderDemo();
        System.out.println(b.i);

        InitOrderDemo b2 = new InitOrderDemo(10, 10);
        System.out.println(b2.i);
    }
}
