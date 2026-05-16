package manfred.exercises.lang.generic;
/**
 * 演示 Java 泛型类与泛型方法的基本定义和使用。
 *
 * 泛型类 JavaGenericClass&lt;T&gt; 封装单个类型参数的字段，
 * 并包含独立的泛型方法 convert&lt;M&gt;，
 * 展示类级别泛型参数与方法级别泛型参数的独立声明方式。
 */
public class JavaGenericClass<T> {

    private T a;

    public JavaGenericClass(T a) {
        this.a = a;
    }

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    public <M> M convert(M m) {
        return m;
    }

    public static void main(String[] args) {
        System.out.println("hello, world");
    }


}
