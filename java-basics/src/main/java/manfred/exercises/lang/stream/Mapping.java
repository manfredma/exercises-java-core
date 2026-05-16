package manfred.exercises.lang.stream;

import manfred.exercises.lang.stream.model.Dish;
import static manfred.exercises.lang.stream.model.Dish.menu;
import java.util.*;
import static java.util.stream.Collectors.toList;

/**
 * 演示 Stream 的 map 与 flatMap 操作。
 *
 * map 用于元素级转换（提取字段、计算长度）；
 * flatMap 将流中每个元素映射为子流再合并，用于字符串拆分去重和笛卡尔积生成，
 * 对比两者在处理嵌套结构时的差异。
 */
public class Mapping{

    public static void main(String...args){

        // map
        List<String> dishNames = menu.stream()
                                     .map(Dish::getName)
                                     .collect(toList());
        System.out.println(dishNames);

        // map
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                                         .map(String::length)
                                         .collect(toList());
        System.out.println(wordLengths);

        // flatMap
        words.stream()
                 .flatMap((String line) -> Arrays.stream(line.split("")))
                 .distinct()
                 .forEach(System.out::println);

        // flatMap
        List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
        List<Integer> numbers2 = Arrays.asList(6,7,8);
        List<int[]> pairs =
                        numbers1.stream()
                                .flatMap((Integer i) -> numbers2.stream()
                                                       .map((Integer j) -> new int[]{i, j})
                                 )
                                .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                                .collect(toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));
    }
}