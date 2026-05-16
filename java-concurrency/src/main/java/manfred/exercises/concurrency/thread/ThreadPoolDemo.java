package manfred.exercises.concurrency.thread;

import java.util.concurrent.*;

/**
 * 演示 ThreadPoolExecutor 与 SynchronousQueue 在高并发场景下的动态扩缩容行为。
 *
 * 创建核心线程数为 2、最大线程数为 100、队列为 {@code SynchronousQueue} 的线程池，
 * 由 200 个外部线程并发向池中提交任务，观察线程池在任务激增时快速扩容至最大线程数、
 * 任务减少后线程超时回收的过程，以及 {@code RejectedExecutionException} 的触发时机。
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        int tn = 200;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 100, 1L, TimeUnit.MINUTES, new SynchronousQueue());
        CountDownLatch latch = new CountDownLatch(tn);
        for (int j = 0; j < tn; j++) {
            Runnable s = () -> {
                latch.countDown();
                for (int i = 0; i < 10; i++) {
                    try {

                        Future f = executor.submit(() -> {
                                    try {
                                        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                                    } catch (InterruptedException e) {
                                        // do nothing
                                    }
                                }
                        );
                        System.out.println(f.getClass());
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }

                }
            };
            new Thread(s).start();
        }
    }
}
