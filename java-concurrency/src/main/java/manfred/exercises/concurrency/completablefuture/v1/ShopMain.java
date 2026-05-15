package manfred.exercises.concurrency.completablefuture.v1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 演示手动创建 CompletableFuture 进行异步价格查询的客户端入口。
 *
 * 通过 getPriceAsync 发起异步调用后立即返回，在等待结果期间执行其他任务，
 * 最后调用 Future.get() 阻塞获取结果，体现异步调用的非阻塞优势与最终等待语义。
 */
public class ShopMain {

  public static void main(String[] args) {
    Shop shop = new Shop("BestShop");
    long start = System.nanoTime();
    Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
    long invocationTime = ((System.nanoTime() - start) / 1_000_000);
    System.out.println("Invocation returned after " + invocationTime 
                                                    + " msecs");
    // Do some more tasks, like querying other shops
    doSomethingElse();
    // while the price of the product is being calculated
    try {
        double price = futurePrice.get();
        System.out.printf("Price is %.2f%n", price);
    } catch (ExecutionException | InterruptedException e) {
        throw new RuntimeException(e);
    }
    long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
    System.out.println("Price returned after " + retrievalTime + " msecs");
  }

  private static void doSomethingElse() {
      System.out.println("Doing something else...");
  }

}