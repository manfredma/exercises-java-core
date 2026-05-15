package manfred.exercises.concurrency.lock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示 ReentrantLock 与 Condition 实现线程间等待/通知的生产者消费者模型。
 *
 * 通过 TaskQueue 内部类展示 Condition.await() 与 Condition.signalAll() 的配套使用，
 * 说明 Condition 相较于 Object.wait/notify 在同一把锁上支持多个等待队列的优势。
 */
public class ConditionExample {
    public static void main(String[] args) throws InterruptedException {
        TaskQueue taskQueue = new TaskQueue();
        new Thread(() -> {
            try {
                System.out.println("开始获取任务~~ ");
                System.out.println(taskQueue.getTask());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("开始放置任务");
        new Thread(() -> taskQueue.addTask("ss")).start();
        TimeUnit.SECONDS.sleep(1);
    }
}

class TaskQueue {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private Queue<String> queue = new LinkedList<>();

    public void addTask(String s) {
        lock.lock();
        try {
            queue.add(s);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String getTask() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                condition.await();
            }
            return queue.remove();
        } finally {
            lock.unlock();
        }
    }
}
