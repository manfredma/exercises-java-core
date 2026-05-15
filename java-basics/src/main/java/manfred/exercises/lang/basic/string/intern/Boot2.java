package manfred.exercises.lang.basic.string.intern;

/**
 * 演示 String.intern() 在 JDK 7+ 中的行为（场景二：先赋字面量后 intern）。
 *
 * 与 Boot1 的区别在于字面量 "11" 先于 intern() 调用被加载到常量池，
 * 导致 intern() 不再返回堆对象引用，而是返回常量池中已有的对象，
 * 对比观察两种调用顺序下引用相等性的差异。
 */
public class Boot2 {
    public static void main(String[] args) {
        String s = new String("1");
        String s2 = "1";
        s.intern();
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        String s4 = "11";
        String s5 = s3.intern();
        System.out.println(s3 == s4);
        System.out.println(s3 == s5);
        System.out.println(s4 == s5);
    }
}
