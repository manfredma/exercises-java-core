package manfred.exercises.concurrency.completablefuture;

import manfred.exercises.concurrency.completablefuture.service.BestPriceFinder;
import java.util.List;
import java.util.function.Supplier;

/**
 * BestPriceFinder 的启动入口，对比顺序、并行流与 CompletableFuture 的执行耗时。
 *
 * 依次执行顺序查询、parallelStream 并行查询、CompletableFuture 组合查询，
 * 以及带流式输出的异步打印，通过耗时打印直观展示各方案的性能差异。
 */
public class BestPriceFinderMain {

    private static BestPriceFinder bestPriceFinder = new BestPriceFinder();

    public static void main(String[] args) {
        execute("sequential", () -> bestPriceFinder.findPricesSequential("myPhone27S"));
        execute("parallel", () -> bestPriceFinder.findPricesParallel("myPhone27S"));
        execute("composed CompletableFuture", () -> bestPriceFinder.findPricesFuture("myPhone27S"));
        bestPriceFinder.printPricesStream("myPhone27S");
    }

    private static void execute(String msg, Supplier<List<String>> s) {
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
    }

}