package manfred.exercises.lang.basic;

/**
 * 演示通过 {@link java.lang.System#getProperties()} 读取 JVM 系统属性。
 *
 * 遍历所有系统属性名并逐行打印键值对，涵盖 {@code java.version}、{@code os.name}、
 * {@code user.home} 等常用属性，帮助了解运行时环境信息的获取方式，
 * 也可用于排查不同操作系统或 JDK 版本下的环境差异。
 */
public class SystemPropertiesDemo {
    public static void main(String[] args) {
        for (String stringPropertyName : System.getProperties().stringPropertyNames()) {
            System.out.println(stringPropertyName + "=" + System.getProperty(stringPropertyName));
        }
    }
}
