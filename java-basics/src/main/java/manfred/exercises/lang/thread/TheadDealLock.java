package manfred.exercises.lang.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 演示 Thread.suspend/resume 引发的死锁场景。
 *
 * 消费者线程持有类锁后调用 suspend 挂起自身，生产者线程尝试获取同一类锁时发生死锁，
 * 说明 suspend/resume 已被废弃的原因：挂起时不释放锁，极易造成死锁。
 */
public class TheadDealLock {
    /**
     * 测试Suspend Resume 死锁
     * <p>
     */
    public static void main(String[] args) {
        AtomicReference<String> message = new AtomicReference<>();
        Thread consumer = new Thread(() -> {
            while (true) {
                synchronized (TheadDealLock.class) {
                    while (message.get() == null) {
                        System.out.println("等待接受消息");
                        Thread.currentThread().suspend();
                    }
                    System.out.println("接受消息 => " + message);
                }
            }
        }, "C-----------------------------------");
        consumer.start();

        Thread producer = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (TheadDealLock.class) {
                    message.set("Hello , this is Producer");
                    consumer.resume();

                }
            }
        }, "P-----------------------------------");
        producer.start();
    }


}
