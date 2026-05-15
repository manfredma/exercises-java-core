package manfred.exercises.lang.stream;

/**
 * 演示匿名内部类中 this 关键字的指向规则。
 *
 * 外部类、匿名内部类各自持有同名 value 字段，在匿名 Runnable 的 run 方法中
 * this.value 指向匿名类自身的 value（5），而非外部类或局部变量，
 * 说明匿名内部类与 Lambda 在 this 语义上的本质区别。
 */
public class MeaningOfThis {
    public final int value = 4;

    public void doIt() {
        int value = 6;
        Runnable r = new Runnable() {
            public final int value = 5;

            public void run() {
                int value = 10;
                System.out.println(this.value);
            }
        };
        r.run();
    }

    public static void main(String... args) {
        MeaningOfThis m = new MeaningOfThis();
        m.doIt(); // ???
    }
}