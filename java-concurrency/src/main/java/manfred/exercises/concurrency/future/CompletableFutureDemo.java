package manfred.exercises.concurrency.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * 演示 CompletableFuture 的基本异步任务创建与完成回调用法。
 *
 * 通过 supplyAsync 提交异步计算任务，使用 whenComplete 注册完成回调
 * 同时获取正常结果与异常信息，展示 CompletableFuture 相较于传统 Future
 * 在结果消费与异常处理上的简洁性。
 */
public class CompletableFutureDemo {
    private static Random rand = new Random();
    private static long t = System.currentTimeMillis();

    static int getMoreData() {
        System.out.println("begin to start compute");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end to start compute. passed " + (System.currentTimeMillis() - t) / 1000 + " seconds");
        return rand.nextInt(1000);
    }

    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(CompletableFutureDemo::getMoreData);

        Future<Integer> f = future.whenComplete((v, e) -> {
            System.out.println(v);
            System.out.println(e);
        });
        System.out.println(f.get());

    }
}
