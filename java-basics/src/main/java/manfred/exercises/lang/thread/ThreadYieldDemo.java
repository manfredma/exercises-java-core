package manfred.exercises.lang.thread;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 演示 Thread.yield() 让出 CPU 时间片的行为。
 *
 * 三个线程同时启动，在 i=0 时各自调用 yield 放弃当前时间片进入就绪状态，
 * 由调度器重新决定执行顺序，展示 yield 的非确定性特性及其与 sleep 的区别。
 */
public class ThreadYieldDemo implements Runnable {


    private static final AtomicBoolean LOCK = new AtomicBoolean(false);

    public void run() {
        while (!LOCK.get()) {
            // do nothing
        }
        System.out.println(Thread.currentThread().getName() + " begin--");
        for (int i = 0; i < 5; i++) {
            //当i=0时让出CPU执行权，放弃时间片，进行下一轮调度
            if ((i % 5) == 0) {
                System.out.println(Thread.currentThread().getName() + " yield cpu...");
                //当前线程让出CPU执行权，放弃时间片，进行下一轮调度
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread().getName() + " is over");
    }

    public static void main(String[] args) {

        Thread[] threads = new Thread[3];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(null, new ThreadYieldDemo(), "ThreadYieldDemo-" + i);
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        LOCK.getAndSet(true);
    }
}