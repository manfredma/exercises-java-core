package manfred.exercises.lang.generic.bound;

abstract class ClassBound {
    public abstract void test1();
}

interface InterfaceBound1 {
    void test2();
}

interface InterfaceBound2 {
    void test3();
}

class ParentClass<T extends ClassBound & InterfaceBound1 & InterfaceBound2> {
    private final T item;

    public ParentClass(T item) {
        this.item = item;
    }

    public void test1() {
        item.test1();
    }

    public void test2() {
        item.test2();
    }

    public void test3() {
        item.test3();
    }
}

class SubClass extends ClassBound implements InterfaceBound1, InterfaceBound2 {

    @Override
    public void test1() {
        System.out.println("test1");
    }

    @Override
    public void test2() {
        System.out.println("test2");
    }

    @Override
    public void test3() {
        System.out.println("test3");
    }
}

/**
 * 演示泛型类型参数的多边界约束（T extends Class &amp; Interface1 &amp; Interface2）。
 *
 * ParentClass&lt;T&gt; 要求 T 同时继承 ClassBound 并实现 InterfaceBound1 和 InterfaceBound2，
 * SubClass 同时满足这些约束，Bound 的 main 方法验证多边界泛型的正确使用，
 * 展示多重约束如何保证泛型类型在运行时具备所需的全部能力。
 */
public class Bound {
    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        ParentClass<SubClass> parentClass = new ParentClass<>(subClass);
        parentClass.test1();
        parentClass.test2();
        parentClass.test3();
    }
}