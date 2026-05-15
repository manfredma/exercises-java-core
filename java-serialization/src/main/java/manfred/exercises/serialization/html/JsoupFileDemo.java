package manfred.exercises.serialization.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;

/**
 * 演示使用 Jsoup 解析本地 HTML 文件的基本用法。
 *
 * 练习 Jsoup 的文件输入解析能力：通过 {@code Jsoup.parse(File, charset)}
 * 将本地 HTML 文件加载为 DOM Document 对象，为后续的元素选择与内容提取做基础。
 */
public class JsoupFileDemo {
    public static void main(String[] args) throws Exception{

        Document doc = Jsoup.parse(new File(""), "UTF-8");
        System.out.println(doc);
    }
}