package manfred.exercises.jvm.optimize;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(value = {Mode.All})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Measurement(iterations = 1)
/**
 * 使用 JMH 基准测试量化伪共享（False Sharing）对多线程性能的影响。
 *
 * 对比 runWithPadding（使用缓存行填充的 ValuePadding）和 runWithNoPadding
 * （无填充的 ValueNoPadding）在不同线程数下的吞吐量，通过 @Param 参数化线程数，
 * 直观展示缓存行填充技术消除伪共享后带来的性能提升幅度。
 */
public class FalseSharingDemo {

    public final static long ITERATIONS = 500L * 1000L * 100L;
    @Param(value = {"1", "3", "5", "7"})
    public String threadNumStr;

    public int threadNum;

    private static ValueNoPadding[] noPaddingsLong;
    private static ValuePadding[] paddingsLong;

    public FalseSharingDemo() {

    }

    public static void main(final String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                // benchmark 所在的类的名字，注意这里是使用正则表达式对所有类进行匹配的。
                .include(FalseSharingDemo.class.getSimpleName())
                // 进行 fork 的次数。如果 fork 数是2的话，则 JMH 会 fork 出两个进程来进行测试。
                .forks(1)
                // 预热的迭代次数。
                .warmupIterations(2)
                .build();
        new Runner(opt).run();
    }

    @Benchmark
    public void runWithNoPadding() throws InterruptedException {
        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threads.length; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> noPaddingLongAssignment(finalI));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    @Benchmark
    public void runWithPadding() throws InterruptedException {
        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threads.length; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> paddingLongAssignment(finalI));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    private void paddingLongAssignment(int arrayIndex) {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            paddingsLong[arrayIndex].value = 0L;
        }
    }

    private void noPaddingLongAssignment(int arrayIndex) {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            noPaddingsLong[arrayIndex].value = 0L;
        }
    }

    public final static class ValuePadding {
        protected long p1, p2, p3, p4, p5, p6, p7;
        protected volatile long value = 0L;
        protected long p9, p10, p11, p12, p13, p14;
        protected long p15;
    }

    public final static class ValueNoPadding {
        protected volatile long value = 0L;
    }

    @Setup
    public void prepare() {
        threadNum = Integer.parseInt(threadNumStr);
        noPaddingsLong = new ValueNoPadding[threadNum];
        paddingsLong = new ValuePadding[threadNum];
        for (int i = 0; i < threadNum; i++) {
            paddingsLong[i] = new ValuePadding();
        }
        // 注意：必须与上面的padding数组分开初始化，否则这个数组中的元素不会挨在一起！
        for (int i = 0; i < threadNum; i++) {
            noPaddingsLong[i] = new ValueNoPadding();
        }
    }
}