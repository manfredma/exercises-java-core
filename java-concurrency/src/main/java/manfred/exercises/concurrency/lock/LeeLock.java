package manfred.exercises.concurrency.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 基于 AbstractQueuedSynchronizer 手写的简易独占锁实现。
 *
 * 通过内部 Sync 类重写 tryAcquire、tryRelease 和 isHeldExclusively 三个方法，
 * 演示 AQS 状态位（state）的 CAS 操作实现互斥锁的核心原理，
 * 帮助理解 ReentrantLock、Semaphore 等 JUC 同步器的底层 AQS 机制。
 */
public class LeeLock  {

    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire (int arg) {
            return compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease (int arg) {
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively () {
            return getState() == 1;
        }
    }

    private Sync sync = new Sync();

    public void lock () {
        sync.acquire(1);
    }

    public void unlock () {
        sync.release(1);
    }
}
