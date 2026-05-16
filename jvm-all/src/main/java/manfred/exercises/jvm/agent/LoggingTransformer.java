package manfred.exercises.jvm.agent;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

/**
 * 实现类加载日志记录的 ClassFileTransformer。
 *
 * 实现了 ClassFileTransformer 接口，在每个类被加载时打印类名及其类加载器信息，
 * 用于理解 JVM 类加载过程中字节码转换的时机和可操作空间。返回 null 表示不修改字节码。
 */
public class LoggingTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(
            ClassLoader classLoader, String className,
            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
            byte[] classFileBuffer) {
        System.out.println(
                classLoader + " Load " + className + "[" + this.getClass().getClassLoader() + "]");
        //进行对应类字节码的操作，并返回新字节码数据的byte数组，如果返回null，则代码不对此字节码作任何操作
        return null;
    }
}
