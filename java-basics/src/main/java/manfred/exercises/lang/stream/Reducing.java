package manfred.exercises.lang.stream;

import static manfred.exercises.lang.stream.Dish.menu;
import static java.util.stream.Collectors.*;

/**
 * 演示 Stream.reduce 与 Collectors.reducing 的多种汇总方式。
 *
 * 对比 reducing（三参数）、reducing（方法引用）、map + reduce 和 mapToInt + sum
 * 四种计算菜单总卡路里的写法，说明各方式在可读性与并行友好性上的差异。
 */
public class Reducing {

    public static void main(String ... args) {
        System.out.println("Total calories in menu: " + calculateTotalCalories());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesWithMethodReference());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesWithoutCollectors());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesUsingSum());
    }

    private static int calculateTotalCalories() {
        return menu.stream().collect(reducing(0, Dish::getCalories, (Integer i, Integer j) -> i + j));
    }

    private static int calculateTotalCaloriesWithMethodReference() {
        return menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
    }

    private static int calculateTotalCaloriesWithoutCollectors() {
        return menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
    }

    private static int calculateTotalCaloriesUsingSum() {
        return menu.stream().mapToInt(Dish::getCalories).sum();
    }
}