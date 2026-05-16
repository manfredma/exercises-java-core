package manfred.exercises.lang.basic;

/**
 * 演示 Java 运算符求值顺序与后置自增（i++）的计算时机。
 *
 * 通过三组循环分别验证：字符串拼接中 {@code +} 操作符严格从左到右计算、
 * 方法调用的实参列表按从左到右顺序对各参数求值、
 * 以及连续两次 {@code i++} 时后一次乘法会使用前一次自增后的值作为起点，
 * 帮助厘清后置自增在复合表达式中的副作用与求值边界。
 */
public class OperatorDemo {

    public static void main(String[] args) throws Exception {
        new OperatorDemo().testParameter();
    }

    public void testParameter() {

        // + 号操作符从左到右计算
        for (int i = 0; i < 10; i++) {
            System.out.println("i++" + i++ + ", (i++)" + (i++));
        }

        // 参数按照从左到右计算
        for (int i = 0; i < 10; i++) {
            print(i++, i++);
        }

        // + 号操作符从左到右计算，后一个 i++ 会使用前一个 i++ 的计算结果开始计算
        for (int i = 0; i < 10; i++) {
            System.out.println("i=" + i + ", (i++)(i++)" + (i++) * (i++));
        }
    }

    private void print(int a, int b) {
        System.out.println("a=" + a + ",b=" + b);
    }

}
