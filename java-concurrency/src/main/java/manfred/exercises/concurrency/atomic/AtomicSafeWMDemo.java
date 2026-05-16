package manfred.exercises.concurrency.atomic;

/**
 * 演示原子类 SafeWM 的线程安全边界校验。
 *
 * 通过 setUpper 设置仓库容量上限，验证原子引用保证的可见性与一致性，
 * 展示 AtomicReference 在并发场景下替代 volatile 的用法。
 */
public class AtomicSafeWMDemo {

    public static void main(String[] args) {
        SafeWM safeWM = new SafeWM();
        safeWM.setUpper(10);
        System.out.println("setUpper(10) success, wm: " + safeWM);
    }
}
