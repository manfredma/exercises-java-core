package manfred.exercises.lang.defaultmethod;

/**
 * 演示接口 default 方法的二义性冲突及显式指定解决方式。
 *
 * 当类 C 同时实现接口 A 和 B，且两者都定义了同名 default 方法时，
 * 编译器会报错，必须在实现类中通过 InterfaceName.super.method() 语法显式指定使用哪个接口的实现。
 */
public class Ambiguous{

    public static void main(String... args) {
        new C().hello();
    }

    static interface A{
        public default void hello() {
            System.out.println("Hello from A");
        }
    }

    static interface B {
        public default void hello() {
            System.out.println("Hello from B");
        }
    }

    static class C implements B, A {
        public void hello(){
            A.super.hello();
        }
    }
}