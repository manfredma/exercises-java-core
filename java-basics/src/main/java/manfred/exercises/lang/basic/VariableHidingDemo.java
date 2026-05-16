package manfred.exercises.lang.basic;

/**
 * 演示 Java 字段隐藏（field hiding）行为。
 *
 * 通过将 Child 对象赋给 Parent 类型引用，验证实例字段不支持多态——
 * 访问的字段由引用的静态类型决定，而非运行时实际类型，
 * 与方法覆盖（override）的动态分派机制形成对比。
 */
public class VariableHidingDemo {
    public static void main(String[] args) {
        Child child = new Child();
        Parent parent = child;
        System.out.println(parent.x);
        System.out.println(child.x);
    }

}
