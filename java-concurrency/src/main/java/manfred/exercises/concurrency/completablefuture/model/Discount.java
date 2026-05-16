package manfred.exercises.concurrency.completablefuture.model;

import static manfred.exercises.concurrency.completablefuture.Util.delay;
import static manfred.exercises.concurrency.completablefuture.Util.format;



/**
 * 提供折扣码枚举与折扣计算逻辑，模拟远程折扣服务的延迟调用。
 *
 * 定义 NONE/SILVER/GOLD/PLATINUM/DIAMOND 五级折扣枚举，
 * 通过 applyDiscount 方法解析 Quote 并叠加折扣百分比，
 * 内部使用 delay() 模拟远程调用延迟，配合 CompletableFuture 流水线练习异步组合。
 */
public class Discount {

    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }
    private static double apply(double price, Code code) {
        delay();
        return format(price * (100 - code.percentage) / 100);
    }
}
