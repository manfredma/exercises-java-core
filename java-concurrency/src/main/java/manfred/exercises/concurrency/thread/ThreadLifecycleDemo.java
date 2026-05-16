package manfred.exercises.concurrency.thread;

import java.io.IOException;

/**
 * 演示大量线程处于 TIMED_WAITING 状态时的线程生命周期观测。
 *
 * 程序启动 2000 个线程，每个线程均调用 {@code Thread.sleep(10000000L)} 进入长时间
 * 等待，随后阻塞在 {@code System.in.read()} 使进程保持存活，便于通过 jstack 或
 * VisualVM 等工具观察线程状态（NEW → RUNNABLE → TIMED_WAITING）及内存占用情况。
 */
public class ThreadLifecycleDemo {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 2000; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(10000000L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            });
            thread.start();
        }
        System.in.read();
    }
}
