package manfred.exercises.concurrency.thread;

/**
 * 演示 Thread.setDefaultUncaughtExceptionHandler 全局异常处理。
 *
 * 通过设置默认未捕获异常处理器，捕获主线程抛出的 RuntimeException，
 * 打印线程 ID、异常信息及当前线程 ID，展示 JVM 级别的线程异常兜底机制。
 */
public class ThreadExceptionDemo {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println(
                    t.getId() + ": " + e.toString() + "(current=" + Thread.currentThread().getId() +
                            ")");
            new RuntimeException(e).printStackTrace();
        });

        throw new RuntimeException("随便抛出个异常");
    }
}
