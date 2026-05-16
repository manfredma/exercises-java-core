package manfred.exercises.lang.defaultmethod;

/**
 * 演示接口 default 方法继承中的菱形问题（Diamond Problem）。
 * <p>
 * 接口 B 和 C 都继承自 A，类 D 同时实现 B 和 C；
 * 由于 A 是唯一的 default 方法来源，编译器可以安全地选择 A 的实现，
 * 说明 Java 接口的菱形继承在此场景下不会产生歧义。
 */
public class Diamond {

    public static void main(String... args) {
        new D().hello();
    }

    interface A {
        default void hello() {
            System.out.println("Hello from A");
        }
    }

    interface B extends A {
    }

    interface C extends A {
    }

    static class D implements B, C {

    }
}