package manfred.exercises.lang.defaultmethod;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 演示 List 接口的 default 方法 sort 与 Comparator 的静态工厂方法。
 *
 * 调用 List.sort()（Java 8 新增的 default 方法）并传入
 * Comparator.naturalOrder()（静态方法），
 * 展示 default 方法和静态接口方法共同为 Java 集合 API 带来的易用性提升。
 */
public class Intro{

    public static void main(String...args){

        List<Integer> numbers = Arrays.asList(3, 5, 1, 2, 6);
        // sort is a default method
        // naturalOrder is a static method
        numbers.sort(Comparator.naturalOrder());
        System.out.println(numbers);
   }
}