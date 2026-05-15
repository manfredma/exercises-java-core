package manfred.exercises.lang.thread;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * 演示 synchronized 方法在父子线程间的互斥效果。
 *
 * 父线程和子线程先后调用同一个 synchronized 静态方法 xxx()，
 * 由于方法持有类锁，子线程必须等待父线程释放锁后才能进入，
 * 直观展示 Java 内置锁的互斥语义与线程执行顺序的非确定性。
 */
public class ThreadBasicDemo {
    public static void main(String[] args) {
        System.out.println("父线程 Begin" + Instant.now().toString());

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("子线程 Begin" + Instant.now().toString());
            xxx();
            System.out.println("子线程 End" + Instant.now().toString());
        }).start();
        xxx();
        System.out.println("父线程 End" + Instant.now().toString());
    }


    public synchronized static void xxx() {
        System.out.println("xxx begin +++");
        long begin = System.currentTimeMillis();
        long x = 0;
        while (System.currentTimeMillis() - begin < 10000) {
            x++;
        }
        System.out.println("xxx end +++");
    }

}
