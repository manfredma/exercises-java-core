package manfred.exercises.jvm.agent.basic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 演示使用自定义类加载器从磁盘反复加载同一个类的行为。
 *
 * 每次调用 loadAndExeX() 都会创建新的 DiskClassLoader 实例，通过反射加载并执行
 * 指定类，用于验证不同类加载器实例加载同一类时 JVM 的隔离行为。
 */
public class JavaAgentDemo {
    public static void main(String[] args) throws Exception {
        // new Throwable().printStackTrace(System.out);
        System.out.println("hello, world!");
        loadAndExeX();
        loadAndExeX();
    }

    private static void loadAndExeX() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ClassLoader classLoader2 = new DiskClassLoader("clazz/");
        Class<?> c2 = classLoader2.loadClass("manfred.end.clazz.loader.SampleDataObject");
        System.out.println("\t" + c2 + " 的类加载器是 " + c2.getClassLoader());
        Object o2 = c2.newInstance();
        Method m2 = c2.getMethod("sayHello");
        m2.invoke(o2);
    }
}
