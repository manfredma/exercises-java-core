package manfred.exercises.concurrency.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 使用 AtomicReference 实现无锁线程安全的水位线范围管理。
 *
 * 演示了基于 CAS（Compare-And-Swap）的乐观锁模式：通过 {@code AtomicReference}
 * 持有不可变的 {@code WMRange} 值对象，在 {@code setUpper} 中以自旋 CAS 替代
 * 互斥锁，保证上限与下限的原子性一致更新，避免出现上限小于下限的非法中间状态。
 */
public class SafeWM {
    class WMRange {
        final int upper;
        final int lower;

        WMRange(int upper, int lower) {
            this.upper = upper;
            this.lower = lower;
        }
    }

    final AtomicReference<WMRange> rf = new AtomicReference<WMRange>(
            new WMRange(0, 0)
    );

    void setUpper(int v) {
        WMRange nr;
        WMRange or = rf.get();
        do {
            // 检查参数合法性
            if (v < or.lower) {
                throw new IllegalArgumentException();
            }
            nr = new WMRange(v, or.lower);
        } while (!rf.compareAndSet(or, nr));
    }
}
