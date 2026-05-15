package manfred.exercises.jvm.invoke.method;

/**
 * 演示 JVM 动态分派（Dynamic Dispatch）的核心机制。
 *
 * 通过将 Man 和 Woman 实例赋值给 Human 类型引用并调用 sayHello，
 * 展示 invokevirtual 指令在运行时根据实际对象类型确定调用目标的行为，
 * 是理解多态性与方法重写在 JVM 字节码层面实现的基础示例。
 */
public class DynamicDispatch {
    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }

    static class Woman extends Human {
        @Override
        protected void sayHello() {
            System.out.println("woman say hello");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }

}