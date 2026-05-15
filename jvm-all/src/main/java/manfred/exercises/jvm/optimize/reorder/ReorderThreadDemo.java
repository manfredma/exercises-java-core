package manfred.exercises.jvm.optimize.reorder;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 通过 CyclicBarrier 精确控制两线程并发执行，检测指令重排序现象。
 *
 * path1 执行 a=1; x=b，path2 执行 b=2; y=a，若出现 x==0 且 y==0 则证明发生了
 * 指令重排序。使用 AtomicInteger 统计触发重排的尝试次数，
 * 用于在实验环境中可重复地观测 JMM 指令有序性保证缺失时的并发异常状态。
 */
public class ReorderThreadDemo {
    int a = 0;
    int b = 0;
    int x = -1;
    int y = -1;

    public void path1() {
        a = 1;
        x = b;
    }

    public void path2() {
        b = 2;
        y = a;
    }

    public boolean test() throws InterruptedException {
        a = b = 0;
        x = y = -1;
        CyclicBarrier cy = new CyclicBarrier(2);
        Thread t1 = new Thread(() -> {
            shortWait(100000);
            path1();
        });
        Thread t2 = new Thread(this::path2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        return x == 0 && y == 0;
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        while (true) {
            int index = atomicInteger.addAndGet(1);
            ReorderThreadDemo tt = new ReorderThreadDemo();
            boolean b = tt.test();
            if (b) {
                System.out.println("出现了指令重排的现象！共执行了 " + index + " 次！");
                break;
            }
        }
    }

    public static void shortWait(long interval) {
        long start = System.nanoTime();
        long end;
        do {
            end = System.nanoTime();
        } while (start + interval >= end);
    }
}