package manfred.exercises.lang.thread;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
/**
 * 演示通过反射读取 ThreadLocal 内部存储结构。
 *
 * 利用反射访问 Thread.threadLocals 字段及其内部 table 数组，
 * 遍历 WeakReference 条目读取 value 字段，
 * 揭示 ThreadLocalMap 以弱引用 key 存储线程局部变量的底层实现细节。
 */
public class ThreadLocalDemo {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // 使用反射读取 threadLocal 中的值
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("xxx");

        Field field = Thread.class.getDeclaredField("threadLocals");
        field.setAccessible(true);
        Object map = field.get(Thread.currentThread());
        Class m = map.getClass();
        Field t = m.getDeclaredField("table");
        t.setAccessible(true);
        WeakReference[] o = (WeakReference[]) t.get(map);
        for (WeakReference o1 : o) {
            if (o1 != null) {
                Class ec = o1.getClass();
                Field ecF = ec.getDeclaredField("value");
                ecF.setAccessible(true);
                System.out.println("value: " + ecF.get(o1));
            }
        }

    }
}
