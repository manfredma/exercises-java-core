package manfred.exercises.concurrency.completablefuture.model;

import manfred.exercises.concurrency.completablefuture.util.Util;
import static manfred.exercises.concurrency.completablefuture.util.Util.delay;
import static manfred.exercises.concurrency.completablefuture.util.Util.format;
import java.util.Random;

/**
 * 模拟带网络延迟的同步商品价格查询商店。
 *
 * 返回格式为 "shopName:price:discountCode" 的价格字符串，
 * 通过 delay() 模拟 1 秒的远程 IO 延迟，用于对比
 * 顺序流、并行流与 CompletableFuture 异步流水线在多商店查询场景下的性能表现。
 */
public class Shop {

    private final String name;
    private final Random random;

    public Shop(String name) {
        this.name = name;
        random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return name + ":" + price + ":" + code;
    }

    public double calculatePrice(String product) {
        delay();
        return format(random.nextDouble() * product.charAt(0) + product.charAt(1));
    }

    public String getName() {
        return name;
    }
}
