package manfred.exercises.lang.stream;

import java.util.function.*;

/**
 * 对比标准 Collector 与自定义 PrimeNumbersCollector 的性能差异。
 *
 * 通过多次执行质数分区操作并记录最快耗时，验证自定义 Collector 相较于
 * 内置 partitioningBy 在百万量级数据上的性能表现，
 * 演示如何对 Collector 实现进行基准测量。
 */
public class CollectorHarness {

    public static void main(String[] args) {
        //System.out.println("Partitioning done in: " + execute(PartitionPrimeNumbers::partitionPrimes) + " msecs");
        System.out.println("Partitioning done in: " + execute(PartitionPrimeNumbers::partitionPrimesWithCustomCollector) + " msecs" );
    }

    private static long execute(Consumer<Integer> primePartitioner) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            primePartitioner.accept(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) fastest = duration;
            System.out.println("done in " + duration);
        }
        return fastest;
    }
}