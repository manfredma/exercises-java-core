package manfred.exercises.concurrency.completablefuture.v1;

import static manfred.exercises.concurrency.completablefuture.Util.*;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * v1 版商店类，提供同步价格查询与手动创建 CompletableFuture 的异步价格查询。
 *
 * 对比 getPriceAsync 中显式创建 CompletableFuture 并在新线程中 complete 的写法，
 * 演示 CompletableFuture 最基础的手动异步模式，以及与 supplyAsync 简化写法的差异。
 */
public class Shop {

    private final String name;
    private final Random random;

    public Shop(String name) {
        this.name = name;
        random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread( () -> {
                    double price = calculatePrice(product);
                    futurePrice.complete(price);
        }).start();
        return futurePrice;
    }

    public String getName() {
        return name;
    }

}