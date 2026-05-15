package manfred.exercises.io.file;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

/**
 * 演示使用 NIO 文件属性 API 读取并修改文件的最后修改时间。
 *
 * 通过 {@link java.nio.file.Files#readAttributes} 读取 {@link java.nio.file.attribute.BasicFileAttributes}
 * 获取文件的创建时间、最后访问时间和最后修改时间，并将文件的最后修改时间重置为其创建时间，
 * 递归遍历目录下所有文件，练习 {@link java.io.File} 与 NIO 文件属性接口的综合使用。
 */
public class FileOperationDemo {
    public static void main(String[] args) throws Exception {
        new FileOperationDemo().modifyTime("C:/Users/maxin/Desktop/mxf");
    }

    private void modifyTime(String fileName) throws Exception {
        File fileToChange = new File(fileName);
        if (fileToChange.isDirectory()) {
            String[] files = fileToChange.list();
            for (int i = 0; i < files.length; i++) {
                modifyTime(fileName + "/" + files[i]);
            }
        } else {

            BasicFileAttributes attr = Files.readAttributes(fileToChange.toPath(), BasicFileAttributes.class);

            System.out.println("creationTime: " + attr.creationTime());
            System.out.println("lastAccessTime: " + attr.lastAccessTime());
            System.out.println("lastModifiedTime: " + attr.lastModifiedTime());
            Date filetime = new Date(fileToChange.lastModified());
            System.out.println(filetime.toString());
            System.out.println(fileToChange.setLastModified(attr.creationTime().toMillis()));
            filetime = new Date(fileToChange.lastModified());
            System.out.println(filetime.toString());
        }
    }
}
