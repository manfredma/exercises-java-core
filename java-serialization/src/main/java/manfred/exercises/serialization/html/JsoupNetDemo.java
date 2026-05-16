package manfred.exercises.serialization.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 演示使用 Jsoup 库通过网络抓取并解析 HTML 页面。
 *
 * <p>知识点：
 * <ul>
 *   <li>Jsoup 是 Java 中最常用的 HTML 解析库，支持 CSS 选择器和 DOM 操作</li>
 *   <li>{@link org.jsoup.Jsoup#connect(String)} 发起 HTTP GET 请求并返回连接对象</li>
 *   <li>{@link org.jsoup.Connection#get()} 执行请求，返回解析后的 {@link org.jsoup.nodes.Document} 对象</li>
 *   <li>{@code Document} 是整个 HTML 文档的根节点，可通过 {@code select()}、{@code getElementById()} 等方法提取内容</li>
 *   <li>网络操作可能抛出 {@link java.io.IOException}，调用方需声明或处理受检异常</li>
 * </ul>
 */
public class JsoupNetDemo {
    public static void main(String[] args) throws Exception{
        Document document = Jsoup.connect("http://blog.beifengtz.com/").get();
        System.out.println(document);
    }
}