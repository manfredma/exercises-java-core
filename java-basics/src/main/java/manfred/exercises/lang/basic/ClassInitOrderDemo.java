package manfred.exercises.lang.basic;

/**
 * 演示 Java 类静态成员的初始化顺序。
 *
 * 通过多个静态块与静态字段交替定义，展示 JVM 按照源码从上到下顺序执行静态初始化器的规则：
 * 第一个静态块将 i 赋值为 11，随后静态字段声明将 i 重置为 20，
 * 第二个静态块再将 i 改为 10，最终 main 方法打印的值为 10，
 * 揭示"先赋值后声明"的静态块前向引用与顺序执行的实际效果。
 */
public class ClassInitOrderDemo {

    static {
        i = 11;
    }

    private static int i = 20;

    static {
        System.out.println("before invoke static block, i = " + i);
        i = 10;
        System.out.println("after invoke static block, i = " + i);
    }

    public static void main(String[] args) {
        System.out.println("main - i = " + i);
    }

}
