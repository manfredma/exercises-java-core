package manfred.exercises.lang.datetime;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 演示 Java 8 新旧日期 API 的常见操作与时区转换。
 *
 * 涵盖以下知识点：通过 {@link java.time.Instant#ofEpochMilli(long)} 将毫秒时间戳转换为
 * 不同时区（GMT+8 与 GMT+7）的带时区日期时间；使用旧版 {@link java.text.SimpleDateFormat}
 * 格式化 {@link java.util.Date}；以及使用 {@link java.time.LocalDateTime} 配合
 * {@link java.time.format.DateTimeFormatter} 格式化当前时间并做日期运算（{@code minusDays}），
 * 对比新旧 API 在时区支持与不可变性上的差异。
 */
public class TimeApiDemo {
    private static DateTimeFormatter YEAR_MONTH_DAY_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        Instant instant = Instant.ofEpochMilli(1569344399000L);
        System.out.println(instant.atZone(ZoneId.of("GMT+8")));
        System.out.println(instant.atZone(ZoneId.of("GMT+7")));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        System.out.println(simpleDateFormat.format(new Date()));

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        localDateTime = localDateTime.minusDays(10);
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd")));

        System.out.println(localDateTime.format(YEAR_MONTH_DAY_FORMATTER));
    }
}
