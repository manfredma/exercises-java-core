package manfred.exercises.concurrency.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示 ReentrantLock 显式加锁与解锁的基本用法。
 *
 * 通过两个线程竞争同一把 ReentrantLock，展示 lock/unlock 的使用规范、
 * try-finally 确保锁释放的编程模式，以及线程因获取不到锁而阻塞等待的行为。
 */
public class ReentrantLockExample {
    Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        final ReentrantLockExample t = new ReentrantLockExample();
        new Thread(t::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t::m2).start();
    }

    void m1() {
        try {
            lock.lock(); // 加锁
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("m1() method " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // 解锁
        }
    }

    void m2() {
        lock.lock();
        System.out.println("m2() method");
        lock.unlock();
    }
}