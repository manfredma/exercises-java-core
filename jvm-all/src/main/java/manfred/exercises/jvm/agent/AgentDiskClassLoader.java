package manfred.exercises.jvm.agent;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 从本地磁盘读取 .class 文件字节码并定义类的自定义类加载器。
 *
 * 继承 ClassLoader 并重写 findClass 方法，从指定路径读取类文件的原始字节数组，
 * 再调用 defineClass 将其注册到 JVM 中。用于演示自定义类加载器绕过双亲委派模型
 * 直接从文件系统加载类的机制（在 agent/basic 包下用于 Agent 相关演示）。
 */
public class AgentDiskClassLoader extends ClassLoader {

    private String path;

    public AgentDiskClassLoader(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String fileName = getFileName(name);
        File file = new File(path, fileName);
        try (FileInputStream is = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            int len;
            try {
                while ((len = is.read()) != -1) {
                    bos.write(len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] data = bos.toByteArray();
            return defineClass(name, data, 0, data.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    //获取要加载 的class文件名
    private String getFileName(String name) {
        // 不考虑特殊的类格式
        return name.replace(".", "/") + ".class";
    }

}
