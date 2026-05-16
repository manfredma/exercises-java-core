package manfred.exercises.jvm.optimize;

/**
 * 演示多线程场景下伪共享（False Sharing）对性能的负面影响。
 *
 * 多个线程各自修改 VolatileLong 数组中不同索引的 volatile long 字段，
 * 由于多个字段可能落在同一 CPU 缓存行中，不同核心的写操作会导致缓存行频繁失效，
 * 产生伪共享性能损耗。注释掉的填充字段展示了通过缓存行填充消除伪共享的对比方案。
 */
public class FalseShareDemo implements Runnable {
    public static int NUM_THREADS = 4;
    public final static long ITERATIONS = 50L * 1000L * 1000L;
    private final int arrayIndex;
    private static VolatileLong[] longs;
    public static long SUM_TIME = 0L;

    public FalseShareDemo(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception {
        Thread.sleep(10000);
        for (int j = 0; j < 10; j++) {
            System.out.println(j);
            if (args.length == 1) {
                NUM_THREADS = Integer.parseInt(args[0]);
            }
            longs = new VolatileLong[NUM_THREADS];
            for (int i = 0; i < longs.length; i++) {
                longs[i] = new VolatileLong();
            }
            final long start = System.nanoTime();
            runTest();
            final long end = System.nanoTime();
            SUM_TIME += end - start;
        }
        System.out.println("平均耗时：" + SUM_TIME / 10);
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseShareDemo(i));
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }

    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
    }

    public final static class VolatileLong {
        public volatile long value = 0L;
        // public long p1, p2, p3, p4, p5, p6;     //屏蔽此行
    }
}