package manfred.exercises.jvm.classloader;

/**
 * 演示同一个类加载器实例对同一个类进行重复加载时的 JVM 缓存行为。
 *
 * 使用同一个 DiskClassLoader 实例两次加载相同的类，验证 JVM 不会重复定义
 * 同一个类加载器已加载过的类——第二次调用直接返回缓存结果，不会触发新的
 * defineClass，体现类加载的幂等性和类加载器的类缓存机制。
 */
public class DuplicateLoad {
    public static void main(String[] args) throws Exception {

        ClassLoader classLoader = new DiskClassLoader("jvm/jvm-classloader/clazz/");
        Class<?> c = classLoader.loadClass("manfred.end.clazz.loader.SampleDataObject");
        Class<?> c2 = classLoader.loadClass("manfred.end.clazz.loader.SampleDataObject");
    }
}
