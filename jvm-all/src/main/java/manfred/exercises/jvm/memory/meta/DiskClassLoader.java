package manfred.exercises.jvm.memory.meta;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * 从磁盘读取 .class 文件字节码并定义类的自定义类加载器（meta 包专用）。
 *
 * 供 MetaOom 使用，通过不断创建新的 DiskClassLoader 实例并加载同一个类，
 * 使 Metaspace 中不断累积类元数据，最终触发 OutOfMemoryError: Metaspace，
 * 用于演示类加载器未被回收导致元空间内存泄漏的典型场景。
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
