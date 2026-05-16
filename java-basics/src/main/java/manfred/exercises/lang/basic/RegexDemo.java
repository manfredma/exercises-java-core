package manfred.exercises.lang.basic;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 演示 Java 正则表达式的常用用法。
 *
 * 涵盖 Pattern.compile 与 Matcher.find 的匹配检测、
 * String.split 处理普通分隔符与特殊字符（需转义）的差异，
 * 以及字符类 [,] 与普通逗号分割的等价写法。
 */
public class RegexDemo {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(".*[a-zA-Z]+.*");
        System.out.println(pattern.matcher("1233a4312").find());
        System.out.println(pattern.matcher("aa4312").find());
        System.out.println(pattern.matcher("aa").find());
        System.out.println(pattern.matcher("132123412").find());

        String x = "1x_23_33";
        System.out.println(Arrays.toString(x.split("_")));


        String[] split = "222?222".split("\\?");
        System.out.println(Arrays.toString(split));


        split = "222,222".split("[,]");
        System.out.println(Arrays.toString(split));

        split = "222,222".split(",");
        System.out.println(Arrays.toString(split));
    }
}
