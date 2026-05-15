package manfred.exercises.concurrency.completablefuture;

import java.util.concurrent.Future;

/**
 * 演示 CompletableFuture 异步获取价格的客户端使用方式。
 *
 * 展示非阻塞调用与阻塞等待结果（Future.get）的时序关系，
 * 帮助理解异步调用的调用立即返回特性与最终结果获取的耗时对比。
 */
public class AsyncShopClient {

    public static void main(String[] args) {
        AsyncShop shop = new AsyncShop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPrice("myPhone");
        long incocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + incocationTime + " msecs");
        try {
            System.out.println("Price is " + futurePrice.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrivalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrivalTime + " msecs");
    }
}