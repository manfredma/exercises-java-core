package manfred.exercises.lang.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 演示 SimpleDateFormat 对不同国际化日期格式的格式化输出。
 *
 * 展示同一个 Date 对象在 dd-MM-yy、MM-dd-yyyy、yyyy-MM-dd HH:mm:ss
 * 以及包含星期、时区等完整格式字符串下的输出结果，
 * 练习 SimpleDateFormat 的模式字符与国际化日期处理。
 */
public class I18NDemo {
    public static void main(String[] args) throws ParseException {
        String pattern = "dd-MM-yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        System.out.println(simpleDateFormat.format(date));
        pattern = "MM-dd-yyyy";
        simpleDateFormat = new SimpleDateFormat(pattern);
        System.out.println(simpleDateFormat.format(date));
        pattern = "yyyy-MM-dd HH:mm:ss";
        simpleDateFormat = new SimpleDateFormat(pattern);
        System.out.println(simpleDateFormat.format(date));
        pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
        simpleDateFormat = new SimpleDateFormat(pattern);
        System.out.println(simpleDateFormat.format(date));
    }
}