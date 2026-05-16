package manfred.exercises.jvm.classloader;

import manfred.exercises.jvm.memory.model.SampleDataObject;
import java.lang.reflect.Method;

/**
 * 演示 JVM 类加载器体系结构及双亲委派模型的工作原理。
 *
 * 打印 BootstrapClassLoader、ExtClassLoader、AppClassLoader 的加载路径，
 * 遍历自定义类加载器的父级委派链，并使用 DiskClassLoader 从磁盘加载自定义类，
 * 帮助理解三层类加载器的委派关系和自定义类加载器的使用方式。
 */
public class ClassLoaderDemo {
    public static void main(String[] args) throws Exception {
        String pathSeparator = System.getProperty("path.separator");
        System.out.println("boot class path -> ");
        for (String s : System.getProperty("sun.boot.class.path").split(pathSeparator)) {
            System.out.println("\t" + s);
        }
        System.out.println("ext class path -> ");
        for (String s : System.getProperty("java.ext.dirs").split(pathSeparator)) {
            System.out.println("\t" + s);
        }
        System.out.println("app class path -> ");
        for (String s : System.getProperty("java.class.path").split(pathSeparator)) {
            System.out.println("\t" + s);
        }
        System.out.println("查看自定义 classLoader 的双亲委派模型 -> ");
        ClassLoader classLoader = new CustomerClassLoader();
        while (classLoader != null) {
            System.out.println("\t" + classLoader.getClass() + " -> " + classLoader);
            classLoader = classLoader.getParent();
        }
        System.out.println("使用自定义类的类加载器 ->");
        classLoader = new DiskClassLoader("jvm/jvm-classloader/clazz/");
        Class<?> c = classLoader.loadClass("manfred.end.clazz.loader.SampleDataObject");
        System.out.println("\t" + c + " 的类加载器是 " + c.getClassLoader());
        Object o = c.newInstance();
        Method m = c.getMethod("sayHello");
        m.invoke(o);
    }
}
