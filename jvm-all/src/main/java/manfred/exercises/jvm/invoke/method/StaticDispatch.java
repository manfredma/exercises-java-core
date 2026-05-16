package manfred.exercises.jvm.invoke.method;

/**
 * 演示 JVM 静态分派（Static Dispatch）机制，即方法重载的解析规则。
 *
 * 将 Man 和 Woman 实例声明为 Human 静态类型，调用重载的 sayHello 方法时，
 * 编译器根据静态类型（Human）而非实际类型（Man/Woman）选择目标方法，
 * 展示了重载在编译期静态确定、与运行时实际类型无关的本质。
 */
public class StaticDispatch {
    static abstract class Human {

    }

    static class Man extends Human {

    }

    static class Woman extends Human {

    }

    public static void main(String[] args) throws Exception {
        new StaticDispatch().test();
    }

    public void test() {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);

    }


    public void sayHello(Human guy) {
        System.out.println("Hello guy");
    }

    public void sayHello(Man guy) {
        System.out.println("Hello man");
    }

    public void sayHello(Woman guy) {
        System.out.println("Hello woman");
    }
}