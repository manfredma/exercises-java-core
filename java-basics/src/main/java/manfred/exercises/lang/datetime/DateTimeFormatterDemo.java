package manfred.exercises.lang.datetime;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 演示 Java 8 日期时间格式化的两种方式：新 API {@link DateTimeFormatter} 与旧 API {@link SimpleDateFormat}。
 *
 * <p>知识点：
 * <ul>
 *   <li>{@link DateTimeFormatter} 是线程安全的（不可变），推荐在多线程环境中共享使用</li>
 *   <li>{@code DateTimeFormatter.ofPattern(String)} 根据模式字符串创建格式化器，模式字母区分大小写（如 {@code MM} 为月，{@code mm} 为分钟）</li>
 *   <li>{@code format(TemporalAccessor)} 将日期时间对象转为字符串；{@code parse(CharSequence, DateTimeFormatter)} 将字符串解析为日期时间对象</li>
 *   <li>{@link SimpleDateFormat} 是非线程安全的旧 API，多线程下需用 {@code ThreadLocal} 包装或每次新建实例</li>
 *   <li>{@link java.util.Date} 代表时间戳（毫秒），与新 API 的 {@link LocalDateTime} 之间需要通过 {@code ZoneId} 转换</li>
 * </ul>
 */
public class DateTimeFormatterDemo {
    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTimeFormatter.format(LocalDateTime.now()));
        System.out.println(dateTimeFormatter.format(LocalDateTime.parse(dateTimeFormatter.format(LocalDateTime.now()), dateTimeFormatter)));

        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        System.out.println(f.format(new Date()));
    }
}
