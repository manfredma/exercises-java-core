package manfred.exercises.concurrency.publish;

/**
 * 演示对象不安全发布导致的构造函数逸出问题。
 *
 * 通过多个读线程并发观察 Holder 字段值，与不断重新赋值的写线程竞争，
 * 复现 Java 内存模型中对象发布不安全时可能读到部分初始化状态的问题，
 * 说明在多线程环境下需通过 volatile、final 或同步机制保证安全发布。
 */
public class UnsafePublishDemo {

    public static void main(String[] args) {
        Init init = new Init();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (; ; ) {
                    if (init.holder.n != 42 || init.holder.a != 42 || init.holder.j != 42) {
                        System.out.println("构造函数溢出了！");
                    }
                }
            }).start();
        }

        new Thread(() -> {
            for (; ; ) {
                init.initialize();
            }
        }).start();

    }
}
