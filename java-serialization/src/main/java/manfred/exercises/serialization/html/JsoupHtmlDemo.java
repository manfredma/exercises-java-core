package manfred.exercises.serialization.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


/**
 * 演示使用 Jsoup 解析 HTML 字符串的基本用法。
 *
 * 练习 Jsoup 的字符串解析能力：通过 {@code Jsoup.parse(String)} 将 HTML 文本
 * 直接解析为 DOM Document 对象，适用于处理内存中的 HTML 片段或完整页面内容。
 */
public class JsoupHtmlDemo {
    public static void main(String[] args) {
        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<meta charset=\"utf-8\">\n" +
                "\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "\t<title>测试网页</title>\n" +
                "\t<link rel=\"stylesheet\" href=\"\">\n" +
                "</head>\n" +
                "<body>\n" +
                "\t<p>这是p标签的内容</p>\n" +
                "\t<a href=\"http://blog.beifengtz.com\" title=\"beifengtz's blog\">beifeng blog</a>\n" +
                "</body>\n" +
                "</html>";
        Document document = Jsoup.parse(html);  //  将字符串解析成Document对象
        System.out.println(document);

    }
}