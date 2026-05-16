package manfred.exercises.lang.stream;

import manfred.exercises.lang.stream.model.Dish;
import static manfred.exercises.lang.stream.model.Dish.menu;
import java.util.*;
import static java.util.stream.Collectors.toList;


/**
 * 演示 Stream 的过滤与截断操作。
 *
 * 涵盖 filter（谓词过滤）、distinct（去重）、limit（截断取前 N 个）
 * 和 skip（跳过前 N 个元素）四种基本流筛选操作的用法与组合。
 */
public class Filtering{

    public static void main(String...args){

        // Filtering with predicate
        List<Dish> vegetarianMenu =
            menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());

        vegetarianMenu.forEach(System.out::println);

        // Filtering unique elements
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
               .filter(i -> i % 2 == 0)
               .distinct()
               .forEach(System.out::println);

        // Truncating a stream
        List<Dish> dishesLimit3 =
            menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(toList());

        dishesLimit3.forEach(System.out::println);

        // Skipping elements
        List<Dish> dishesSkip2 =
            menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(toList());

        dishesSkip2.forEach(System.out::println);
    }
}