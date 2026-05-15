package manfred.exercises.lang.basic;

/**
 * 演示 Java 基础语法中的边界情况与陷阱。
 *
 * 涵盖 null 与字符串拼接的行为（null + "" 会得到字符串 "null"）、
 * 三目运算符触发自动拆箱的潜在 NullPointerException、
 * 以及 Math.abs(Integer.MIN_VALUE) 返回负数这一整型溢出陷阱。
 */
public class Basic {
    public static void main(String[] args) {
        System.out.println(null + "");
        System.out.println((String) null);
        String s = null + "";
        System.out.println(s.length());

        // 三目运算符
        System.out.println(false ? 1 : Integer.valueOf(2));

        System.out.println(Math.abs(Integer.MIN_VALUE));
        System.out.println(Math.abs(Integer.MAX_VALUE));
        System.out.println(Integer.MIN_VALUE);

    }
}
