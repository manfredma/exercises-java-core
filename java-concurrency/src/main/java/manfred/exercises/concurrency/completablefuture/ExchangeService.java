package manfred.exercises.concurrency.completablefuture;

import static manfred.exercises.concurrency.completablefuture.Util.delay;


/**
 * 模拟带网络延迟的货币汇率查询服务。
 *
 * 定义 USD/EUR/GBP/CAD/MXN 汇率枚举，通过 delay() 模拟远程 IO 耗时，
 * 配合 CompletableFuture.thenCombine 演示两个异步任务（价格查询 + 汇率查询）的并行合并。
 */
public class ExchangeService {

    public enum Money {
        USD(1.0), EUR(1.35387), GBP(1.69715), CAD(.92106), MXN(.07683);

        private final double rate;

        Money(double rate) {
            this.rate = rate;
        }
    }

    public static double getRate(Money source, Money destination) {
        return getRateWithDelay(source, destination);
    }

    private static double getRateWithDelay(Money source, Money destination) {
        delay();
        return destination.rate / source.rate;
    }

}