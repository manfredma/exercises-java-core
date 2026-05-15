package manfred.exercises.io.bio.m2n;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * BIO 伪异步模型中用于分发客户端处理任务的线程池封装。
 *
 * 内部使用 {@link ThreadPoolExecutor} 和有界 {@link java.util.concurrent.ArrayBlockingQueue}，
 * 演示如何通过控制最大线程数与队列容量来防止资源耗尽，
 * 是 BIO 模型从"一连接一线程"升级到"伪异步 I/O"的核心组件。
 */
public class TimeServerHandlerExecutorPool {
    private ExecutorService executorService;

    public TimeServerHandlerExecutorPool(int maxPoolSize, int queueSize) {
        executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueSize));
    }

    public void execute(Runnable runnable) {
        executorService.execute(runnable);
    }

}
