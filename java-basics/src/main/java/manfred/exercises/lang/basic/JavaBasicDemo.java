package manfred.exercises.lang.basic;

/**
 * 演示浮点数的精度问题。
 *
 * 直接打印 double 类型的小数（如 0.01~0.09），
 * 观察 IEEE 754 双精度浮点数表示引起的精度偏差，
 * 说明在财务计算中应使用 BigDecimal 而非 double。
 */
public class JavaBasicDemo {
    public static void main(String[] args) {
        System.out.println(0.01);
        System.out.println(0.02);
        System.out.println(0.03);
        System.out.println(0.04);
        System.out.println(0.05);
        System.out.println(0.06);
        System.out.println(0.07);
        System.out.println(0.08);
        System.out.println(0.090000000000000000000006);
    }
}
