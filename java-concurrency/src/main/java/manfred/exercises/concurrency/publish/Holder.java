package manfred.exercises.concurrency.publish;

/**
 * 演示不安全发布场景中的值持有对象。
 *
 * 持有三个公开可变字段 n、a、j，在构造时均赋予同一值，
 * 用于在不安全发布场景下验证多线程读取时是否可能观察到字段值不一致的中间状态，
 * 体现构造函数未完成时对象引用被外部线程访问的风险。
 */
public class Holder {
    public int n;

    public int a;

    public int j;

    public Holder(int n) {
        this.n = n;
        this.a = n;
        this.j = n;
    }

    public void assertSanity() {
        if (n != n) {
            throw new AssertionError("This statement is false.");
        }
    }
}