package manfred.exercises.lang.datetime;

import java.time.LocalDate;
import java.time.ZoneId;

/**
 * 演示 Java 8 {@link LocalDate} 的基本用法，包括解析、获取当天日期及与时区相关的转换。
 *
 * <p>知识点：
 * <ul>
 *   <li>{@link LocalDate} 仅表示日期（年月日），不包含时间和时区信息，适合生日、节假日等场景</li>
 *   <li>{@code LocalDate.parse(String)} 默认按 ISO-8601 格式（{@code yyyy-MM-dd}）解析字符串</li>
 *   <li>{@code LocalDate.now()} 获取系统默认时区下的当天日期</li>
 *   <li>{@code atStartOfDay(ZoneId)} 将 {@code LocalDate} 转为当天零点的 {@link java.time.ZonedDateTime}，再通过 {@code toInstant()} 获得时间戳</li>
 *   <li>{@code atStartOfDay()} 不带参数时返回 {@link java.time.LocalDateTime}，不含时区偏移信息</li>
 *   <li>{@link ZoneId#systemDefault()} 获取 JVM 默认时区，该值可能因环境而异，生产代码中推荐显式指定时区</li>
 * </ul>
 */
public class LocalDateDemo {
    public static void main(String[] args) {
        System.out.println(LocalDate.parse("2020-02-02"));
        System.out.println(LocalDate.parse("2020-02-02"));

        System.out.println(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(LocalDate.now().atStartOfDay());
    }
}
