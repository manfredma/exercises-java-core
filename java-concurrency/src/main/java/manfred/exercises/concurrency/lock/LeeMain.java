package manfred.exercises.concurrency.lock;

import java.util.concurrent.TimeUnit;

/**
 * 验证自定义 AQS 锁 LeeLock 的互斥正确性。
 *
 * 启动两个线程并发对共享变量 count 执行累加，通过 LeeLock 保证互斥访问，
 * 最终打印结果验证是否为期望的 20000，测试自定义 AQS 锁的线程安全性。
 */
public class LeeMain {

    static int count = 0;
    static LeeLock leeLock = new LeeLock();

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = () -> {
            try {
                leeLock.lock();
                System.out.println(Thread.currentThread() + "开始执行");
                TimeUnit.SECONDS.sleep(1L);
                for (int i = 0; i < 10000; i++) {
                    count++;
                }
                System.out.println(Thread.currentThread() + "执行结束");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                leeLock.unlock();
            }

        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(count);
    }
}
