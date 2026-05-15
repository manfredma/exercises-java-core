package manfred.exercises.lang.defaultmethod;

/**
 * 演示接口 default 方法的"最具体实现优先"解析规则。
 *
 * 通过类 C（实现 B 和 A，B extends A）、类 E（继承 D 同时实现 B 和 A）、
 * 类 G（继承覆盖了方法的 F）三个场景，
 * 说明 Java 在 default 方法冲突时优先选择子接口或类中更具体实现的规则。
 */
public class MostSpecific{

    public static void main(String... args) {
        new C().hello();
        new E().hello();
        new G().hello();
    }

    static interface A{
        public default void hello() {
            System.out.println("Hello from A");
        }
    }

    static interface B extends A{
        public default void hello() {
            System.out.println("Hello from B");
        }
    }

    static class C implements B, A {}

    static class D implements A{}

    static class E extends D implements B, A{}

    static class F implements B, A {
        public void hello() {
            System.out.println("Hello from F");
        }
    }

    static class G extends F implements B, A{}

}
