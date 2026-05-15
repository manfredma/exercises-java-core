package manfred.exercises.lang.gc;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * 演示 WeakReference 在 GC 后被回收的行为。
 *
 * 创建大量 WR 对象（WeakReference 子类），触发 System.gc() 后等待，
 * 观察弱引用的 key 部分（referent）被回收变为 null，
 * 而强引用的 value 部分仍然存在，帮助理解四种引用类型的生命周期差异。
 */
public class WeakReferenceDemo {

    public static void main(String[] args) throws InterruptedException {
        WR wr = new WR(new Object(), new Object());
        for (int i = 0; i < 10000L; i++) {
            wr = new WR(new Object(), new Object());
        }
        System.out.println(wr);
        System.gc();
        TimeUnit.SECONDS.sleep(2L);
        System.out.println(wr);
    }
}


class WR extends WeakReference<Object> {

    Object value;

    public WR(Object referent, Object value) {
        super(referent);
        this.value = value;
    }

    public String toString() {
        return "key: " + super.get() + ", value: " + value;
    }

}