package manfred.exercises.concurrency.coroutine.ea;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.ea.async.Async.await;

/**
 * 演示使用 EA Async 库将 CompletableFuture 异步代码转换为同步风格写法。
 *
 * 对比三种实现方式：原生 CompletableFuture 链式调用（test）、
 * EA Async 的 await 关键字简化写法（testAsync），
 * 以及直接调用 CompletableFuture.get() 阻塞等待（testAsyncV2），
 * 体现协程风格异步编程对代码可读性的提升。
 */
public class TestEaAsync {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(LocalDateTime.now() + " begin ====================================");
        String result = test().join();
        System.out.println(result);
        System.out.println(LocalDateTime.now() + " end1 ====================================");
        System.out.println(testAsync());
        System.out.println(LocalDateTime.now() + " end2 ====================================");
        System.out.println(testAsyncV2());
        System.out.println(LocalDateTime.now() + " end3 ====================================");
    }

    public static CompletableFuture<String> test() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Manfred";
        }), (s1, s2) -> s1 + " " + s2);
    }

    public static String testAsync() {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "async Hello";
        });
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Manfred";
        });
        return await(cf1) + " " + await(cf2);
    }

    public static String testAsyncV2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "asyncV2 Hello";
        });
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Manfred";
        });
        return cf1.get() + " " + cf2.get();
    }
}
 
