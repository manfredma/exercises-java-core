package manfred.exercises.lang.string;

/**
 * 演示 String.intern() 在 JDK 7+ 中的行为（场景一：先 intern 后赋字面量）。
 *
 * 通过对比 new String 对象、intern() 返回值与字面量常量池引用的内存地址，
 * 验证 JDK 7 起 intern() 会将堆中已有对象的引用放入常量池，
 * 与 StringInternDemo2 形成先后顺序不同导致结果差异的对比实验。
 * 参考：https://tech.meituan.com/2014/03/06/in-depth-understanding-string-intern.html
 */
public class StringInternDemo1 {
    public static void main(String[] args) {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        String s5 = s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
        System.out.println(s3 == s5);
        System.out.println(s4 == s5);

        System.out.println(System.identityHashCode(s));
        System.out.println(System.identityHashCode(s2));
        System.out.println(System.identityHashCode(s3));
        System.out.println(System.identityHashCode(s4));
        System.out.println(System.identityHashCode(s5));
    }
}
