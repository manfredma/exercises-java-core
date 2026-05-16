package manfred.exercises.concurrency.completablefuture;

import manfred.exercises.concurrency.completablefuture.service.V1BestPriceFinder;
import manfred.exercises.concurrency.completablefuture.service.BestPriceFinder;
import java.util.List;
import java.util.function.Supplier;

/**
 * v1 版 BestPriceFinder 的启动入口，对比多种价格查询方案的执行耗时。
 *
 * 依次运行顺序查询、并行流、CompletableFuture 组合、含汇率换算的 USD 价格查询
 * 以及多个变体实现，通过耗时输出直观展示各并发方案的性能差异。
 */
public class V1BestPriceFinderMain {

    private static V1BestPriceFinder bestPriceFinder = new V1BestPriceFinder();

    public static void main(String[] args) {
        execute("sequential", () -> bestPriceFinder.findPricesSequential("myPhone27S"));
        execute("parallel", () -> bestPriceFinder.findPricesParallel("myPhone27S"));
        execute("composed CompletableFuture", () -> bestPriceFinder.findPricesFuture("myPhone27S"));
        execute("combined USD CompletableFuture", () -> bestPriceFinder.findPricesInUSD("myPhone27S"));
        execute("combined USD CompletableFuture v2", () -> bestPriceFinder.findPricesInUSD2("myPhone27S"));
        execute("combined USD CompletableFuture v3", () -> bestPriceFinder.findPricesInUSD3("myPhone27S"));
    }

    private static void execute(String msg, Supplier<List<String>> s) {
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
    }
}
