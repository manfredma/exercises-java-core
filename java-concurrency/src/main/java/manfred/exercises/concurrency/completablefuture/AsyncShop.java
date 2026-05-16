package manfred.exercises.concurrency.completablefuture;

import manfred.exercises.concurrency.completablefuture.util.Util;
import static manfred.exercises.concurrency.completablefuture.util.Util.delay;
import static manfred.exercises.concurrency.completablefuture.util.Util.format;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * 演示使用 CompletableFuture 实现异步商品价格查询的商店服务。
 *
 * 展示两种异步实现方式：手动创建 CompletableFuture 并在新线程中完成，
 * 以及使用 CompletableFuture.supplyAsync 简化异步编程，同时展示
 * completeExceptionally 处理异步任务中的异常传播。
 */
public class AsyncShop {

    private final String name;
    private final Random random;

    public AsyncShop(String name) {
        this.name = name;
        random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public Future<Double> getPrice(String product) {
/*
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread( () -> {
                    try {
                        double price = calculatePrice(product);
                        futurePrice.complete(price);
                    } catch (Exception ex) {
                        futurePrice.completeExceptionally(ex);
                    }
        }).start();
        return futurePrice;
*/
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        delay();
        if (true) throw new RuntimeException("product not available");
        return format(random.nextDouble() * product.charAt(0) + product.charAt(1));
    }

}