package manfred.exercises.jvm.memory;

import manfred.exercises.jvm.classloader.MetaDiskClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * 演示通过不断加载类导致 JVM Metaspace 内存溢出（OutOfMemoryError: Metaspace）。
 *
 * 无限循环中持续创建新的 DiskClassLoader 并加载同一个类，同时将 ClassLoader
 * 引用保存在列表中防止 GC 回收，使得 Metaspace 中的类元数据只增不减，
 * 最终耗尽元空间，用于理解类加载器内存泄漏与 Metaspace OOM 的关联。
 */
public class MetaOom {
    public static void main(String[] args) throws Exception {
        // 将 classLoader 保存在方法内部变量里面，防止类卸载
        List<ClassLoader> classLoaderList = new ArrayList<>();
        while (true) {
            ClassLoader classLoader = new MetaDiskClassLoader("jvm/jvm-classloader/clazz/");
            classLoaderList.add(classLoader);
            Class<?> c = classLoader.loadClass("manfred.end.clazz.loader.SampleDataObject");
        }
    }
}
