package manfred.exercises.jvm.classloader;

/**
 * 最简自定义类加载器，用于观察双亲委派模型的触发过程。
 *
 * 重写 findClass 方法并打印日志，当双亲委派链无法加载指定类时此方法才会被调用，
 * 用于验证双亲委派模型"先委托父级，父级无法加载时才自己加载"的核心逻辑。
 */
public class CustomerClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("尝试加载-" + name);
        return super.findClass(name);
    }
}
