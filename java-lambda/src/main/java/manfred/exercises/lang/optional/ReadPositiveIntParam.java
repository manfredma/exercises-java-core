package manfred.exercises.lang.optional;

import java.util.*;

import static java.util.Optional.*;

/**
 * 对比命令式与 Optional 函数式两种方式读取正整数属性值。
 *
 * readDurationImperative 用传统 if-try 嵌套处理 null 和异常，
 * readDurationWithOptional 用 ofNullable + flatMap + filter + orElse 链式调用替代，
 * 体现 Optional 在消除样板代码方面的优势。
 */
public class ReadPositiveIntParam {

    public static void main(String[] args) throws Exception {
        new ReadPositiveIntParam().testMap();
    }

    public void testMap() {
        Properties props = new Properties();
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-3");

        System.out.println("imperative a: " + readDurationImperative(props, "a") + ", expected: 5");
        System.out.println("imperative b: " + readDurationImperative(props, "b") + ", expected: 0");
        System.out.println("imperative c: " + readDurationImperative(props, "c") + ", expected: 0");
        System.out.println("imperative d: " + readDurationImperative(props, "d") + ", expected: 0");

        System.out.println("optional a: " + readDurationWithOptional(props, "a") + ", expected: 5");
        System.out.println("optional b: " + readDurationWithOptional(props, "b") + ", expected: 0");
        System.out.println("optional c: " + readDurationWithOptional(props, "c") + ", expected: 0");
        System.out.println("optional d: " + readDurationWithOptional(props, "d") + ", expected: 0");
    }

    public static int readDurationImperative(Properties props, String name) {
        String value = props.getProperty(name);
        if (value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0) {
                    return i;
                }
            } catch (NumberFormatException nfe) { }
        }
        return 0;
    }

    public static int readDurationWithOptional(Properties props, String name) {
        return ofNullable(props.getProperty(name))
                .flatMap(ReadPositiveIntParam::s2i)
                .filter(i -> i > 0).orElse(0);
    }

    public static Optional<Integer> s2i(String s) {
        try {
            return of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return empty();
        }
    }

}
