package manfred.exercises.concurrency.completablefuture.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * CompletableFuture 练习的公共工具类，提供延迟模拟和数字格式化方法。
 *
 * delay() 方法模拟固定 1 秒的远程 IO 延迟；format() 使用同步格式化保证线程安全；
 * sequence() 展示将 List&lt;CompletableFuture&lt;T&gt;&gt; 合并为单个 CompletableFuture 的两种写法对比。
 */
public class Util {

    private static final Random RANDOM = new Random(0);
    private static final DecimalFormat formatter = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

    public static void delay() {
        int delay = 1000;
        //int delay = 500 + RANDOM.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static double format(double number) {
        synchronized (formatter) {
            return new Double(formatter.format(number));
        }
    }

    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
/*
        CompletableFuture<Void> allDoneFuture =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        return allDoneFuture.thenApply(v ->
                futures.stream().
                        map(future -> future.join()).
                        collect(Collectors.<T>toList())
        );
*/
        return CompletableFuture.supplyAsync(() -> futures.stream().
                map(future -> future.join()).
                collect(Collectors.<T>toList()));
    }
}