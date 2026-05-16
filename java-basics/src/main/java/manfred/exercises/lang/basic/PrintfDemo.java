package manfred.exercises.lang.basic;

import java.io.PrintStream;

/**
 * 演示 {@link java.io.PrintStream#printf} 的格式化输出，以及 Java 不支持无符号整数格式符的特殊行为。
 *
 * <p>知识点：
 * <ul>
 *   <li>{@code System.out.printf(format, args)} 等价于 {@code System.out.format(format, args)}，底层调用 {@link java.util.Formatter}</li>
 *   <li>Java 不原生支持 C 语言的 {@code %u}（无符号十进制）格式符，传入 {@code %u} 会抛出 {@link java.util.MissingFormatWidthException} 或格式异常</li>
 *   <li>如需以无符号形式输出 long，可使用 {@link Long#toUnsignedString(long)}；int 对应 {@link Integer#toUnsignedString(int)}（Java 8+）</li>
 *   <li>常用格式符：{@code %d} 十进制整数、{@code %x} 十六进制、{@code %o} 八进制、{@code %f} 浮点数、{@code %s} 字符串、{@code %n} 平台换行符</li>
 *   <li>格式符支持宽度和精度控制，如 {@code %10.2f} 表示总宽 10、保留 2 位小数</li>
 * </ul>
 */
public class PrintfDemo {
    public static void main(String[] args) {
        System.out.printf("%u", 1111111111111111L);
        System.out.printf("%u", -1111111111111111L);
    }
}
