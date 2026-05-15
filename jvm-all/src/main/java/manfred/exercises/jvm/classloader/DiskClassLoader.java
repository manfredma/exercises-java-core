package manfred.exercises.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * 从本地磁盘读取 .class 文件字节码并定义类的自定义类加载器。
 *
 * 继承 ClassLoader 并重写 findClass 方法，从指定目录中读取类文件原始字节，
 * 再调用 defineClass 将其注册到 JVM。用于演示自定义类加载器的实现方式，
 * 以及如何在双亲委派模型之外从文件系统任意路径加载类（classloader 包版本）。
 */
public class DiskClassLoader extends ClassLoader {

    private String path;

    public DiskClassLoader(String path) {
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
